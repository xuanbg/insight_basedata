package com.insight.basedata.file;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务
 */
public interface FileService {

    /**
     * 获取七牛上传令牌
     *
     * @return Reply
     */
    String getToken();

    /**
     * 上传文件
     *
     * @param file 文件DTO
     * @return Reply
     */
    String upload(MultipartFile file);
}
