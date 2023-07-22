package com.insight.basedata.file;

import com.insight.utils.pojo.base.BusinessException;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务实现
 */
@Service
public class FileServiceImpl implements FileService {

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
     * 七牛文件空间bucket
     */
    @Value("${qiniu.bucket}")
    private String bucket;

    /**
     * 获取七牛上传令牌
     *
     * @return Reply
     */
    @Override
    public String getToken() {
        var auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
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
        try {
            var path = Paths.get("/upload/" + name);
            file.transferTo(path);

            return path.toString();
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }
}
