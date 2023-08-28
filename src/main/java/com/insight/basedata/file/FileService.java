package com.insight.basedata.file;

import com.insight.basedata.common.dto.FileDto;
import com.insight.basedata.common.entity.UploadFile;
import org.springframework.web.multipart.MultipartFile;

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
    String upload(MultipartFile file);

    /**
     * 上传文件
     *
     * @param file 文件DTO
     * @return 上传令牌
     */
    UploadFile addFileToQiniu(FileDto file);

    /**
     * 获取指定的文件路径
     *
     * @param id 文件ID
     * @return 文件路径
     */
    String getFileUrl(Long id);
}
