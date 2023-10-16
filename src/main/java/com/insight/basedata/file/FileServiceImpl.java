package com.insight.basedata.file;

import com.insight.basedata.common.Core;
import com.insight.basedata.common.dto.FileDto;
import com.insight.basedata.common.entity.File;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务实现
 */
@Service
public class FileServiceImpl implements FileService {
    private final Core core;

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
     * @param core 核心功能类
     */
    public FileServiceImpl(Core core) {
        this.core = core;
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
    public File addFileToQiniu(FileDto file) {
        file.setParentId(core.addFolder(file));
        file.setDomain(domain);

        var auth = Auth.create(accessKey, secretKey);
        var data = core.addFile(file);
        if (!data.getExisted()){
            data.setToken(auth.uploadToken(bucket));
            data.setBucket(bucket);
        }

        return data.convert(File.class);
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
        var file = core.getFile(id);
        long deadline = System.currentTimeMillis() / 1000 + 3600;

        try {
            var url = new DownloadUrl(domain, true, file);
            return url.buildURL(auth, deadline);
        } catch (QiniuException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
