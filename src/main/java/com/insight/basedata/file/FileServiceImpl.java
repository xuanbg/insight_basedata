package com.insight.basedata.file;

import com.insight.basedata.common.dto.FileDto;
import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.base.Reply;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务实现
 */
@Service
public class FileServiceImpl implements FileService {
    private final Configuration cfg = new Configuration(Region.region0());
    private final UploadManager uploadManager = new UploadManager(cfg);

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
     * 上传文件
     *
     * @param file 文件DTO
     * @return Reply
     */
    @Override
    public Reply upload(FileDto file) throws QiniuException {
        try {
            String token = getUploadToken();
            Response response = uploadManager.put(file.getBytes(), file.getName(), token);
            DefaultPutRet putRet = Json.toBean(response.bodyString(), DefaultPutRet.class);

            return ReplyHelper.success(putRet.key);
        } catch (QiniuException ex) {
            Response response = ex.response;
            return ReplyHelper.fail(response.bodyString());
        }
    }

    /**
     * 获取七牛上传令牌
     *
     * @return Reply
     */
    @Override
    public Reply getToken() {
        return ReplyHelper.success(getUploadToken());
    }

    /**
     * 获取七牛上传令牌
     *
     * @return 上传令牌
     */
    private String getUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
