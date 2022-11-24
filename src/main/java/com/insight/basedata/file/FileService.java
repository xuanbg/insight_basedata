package com.insight.basedata.file;

import com.insight.basedata.common.dto.FileDto;
import com.insight.utils.pojo.base.Reply;
import com.qiniu.common.QiniuException;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file 文件DTO
     * @return Reply
     */
    Reply upload(FileDto file) throws QiniuException;

    /**
     * 获取七牛上传令牌
     *
     * @return Reply
     */
    Reply getToken();
}
