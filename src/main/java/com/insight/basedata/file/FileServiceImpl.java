package com.insight.basedata.file;

import com.insight.basedata.common.mapper.FileMapper;
import com.insight.utils.SnowflakeCreator;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.file.FileDto;
import com.insight.utils.pojo.file.FileVo;
import com.insight.utils.pojo.file.Folder;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务实现
 */
@Service
public class FileServiceImpl implements FileService {
    private final SnowflakeCreator creator;
    private final FileMapper mapper;

    /**
     * 七牛文件域名
     */
    @Value("${qiniu.domain}")
    private String domain;

    /**
     * 七牛文件空间bucket
     */
    @Value("${qiniu.bucket}")
    private String bucket;

    /**
     * 七牛accessKey
     */
    @Value("${qiniu.accessKey}")
    private String accessKey;

    /**
     * 七牛secretKey
     */
    @Value("${qiniu.secretKey}")
    private String secretKey;

    /**
     * 构造方法
     *
     * @param creator ID生成器
     * @param mapper  文件DAL
     */
    public FileServiceImpl(SnowflakeCreator creator, FileMapper mapper) {
        this.creator = creator;
        this.mapper = mapper;
    }

    /**
     * 上传文件
     *
     * @param file 文件DTO
     * @return Reply
     */
    @Override
    public String upload(MultipartFile file) {
        var name = file.getOriginalFilename();
        if (Util.isEmpty(name)) {
            throw new BusinessException("文件名不能为空");
        }

        var path = "/files/" + Util.uuid() + name.substring(name.lastIndexOf("."));
        try {
            file.transferTo(Paths.get(path));
            return path;
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件DTO
     * @return 上传令牌
     */
    @Override
    @Transactional
    public FileVo addFileToQiniu(FileDto file) {
        var ownerId = file.getOwnerId();
        var data = mapper.getFileByHash(file.getHash(), ownerId);
        if (data != null) {
            return data;
        }

        // 获取文件夹信息
        var records = mapper.getFolders(ownerId);
        var array = file.getFullPath().split("/");
        var folders = new ArrayList<>(Arrays.stream(array).limit(array.length - 1).toList());
        if (folders.isEmpty()) {
            var folder = switch (file.getType()) {
                case 1 -> "picture";
                case 2 -> "audio";
                case 3 -> "video";
                case 4 -> "document";
                default -> "other";
            };
            folders.add(ownerId.toString());
            folders.add(folder);
        }

        // 保存文件夹信息
        for (var name : folders) {
            if (Util.isEmpty(name)) {
                continue;
            }

            var parentId = file.getParentId();
            var record = records.stream().filter(i -> i.equals(parentId, name)).findFirst().orElse(null);
            if (record == null) {
                var folder = new Folder();
                folder.setId(creator.nextId(5));
                folder.setParentId(parentId);
                folder.setOwnerId(ownerId);
                folder.setName(name);

                mapper.addFolder(folder);
                file.setParentId(folder.getId());
            } else {
                file.setParentId(record.getId());
            }
        }

        // 保存文件信息
        file.setId(creator.nextId(5));
        file.setDomain(domain);
        data = mapper.getFileByHash(file.getHash(), null);
        if (data != null) {
            file.setPath(data.getPath());
        }

        mapper.addFile(file);

        // 返回文件信息
        var result = file.convert(FileVo.class);
        if (data == null) {
            var auth = Auth.create(accessKey, secretKey);
            var token = auth.uploadToken(bucket);
            if (Util.isEmpty(token)) {
                throw new BusinessException("上传令牌获取失败");
            }

            result.setToken(token);
            result.setBucket(bucket);
        }

        return result;
    }

    /**
     * 获取指定的文件路径
     *
     * @param id 文件ID
     * @return 文件路径
     */
    @Override
    public String getFileUrl(Long id) {
        var auth = Auth.create(accessKey, secretKey);
        var file = mapper.getFile(id);
        long deadline = System.currentTimeMillis() / 1000 + 3600;

        try {
            var url = new DownloadUrl(domain, true, file);
            return url.buildURL(auth, deadline);
        } catch (QiniuException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
