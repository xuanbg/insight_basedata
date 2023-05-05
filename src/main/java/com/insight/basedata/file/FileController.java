package com.insight.basedata.file;

import com.insight.basedata.common.dto.FileDto;
import com.insight.utils.pojo.base.BusinessException;
import com.qiniu.common.QiniuException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/common/file")
public class FileController {
    private final FileService service;

    /**
     * 构造方法
     *
     * @param service 文件管理服务
     */
    public FileController(FileService service) {
        this.service = service;
    }

    /**
     * 获取七牛上传令牌
     *
     * @return Reply
     */
    @GetMapping("/v1.0/token")
    public String getToken() {
        return service.getToken();
    }

    /**
     * 直接上传文件
     *
     * @param file 文件
     * @return Reply
     */
    @PostMapping("/v1.0/upload")
    public String uploadFile(@RequestParam MultipartFile file) throws IOException {
        if (file == null || file.getOriginalFilename() == null) {
            throw new BusinessException("文件不能为空");
        }

        String contentType = file.getContentType();
        String ext = contentType == null ? null : contentType.substring(contentType.lastIndexOf("/") + 1);

        FileDto dto = new FileDto();
        dto.setStream(file.getInputStream());
        dto.setName(file.getName());
        dto.setExt(ext);

        return service.upload(dto);
    }

    /**
     * 使用数据流上传文件
     *
     * @param file 数据流
     * @return Reply
     */
    @PostMapping("/v1.0/stream")
    public String upload(@RequestParam InputStream file) throws QiniuException {
        FileDto dto = new FileDto();
        dto.setStream(file);

        return service.upload(dto);
    }

    /**
     * 使用字节数组上传文件
     *
     * @param file 文件DTO
     * @return Reply
     */
    @PostMapping("/v1.0/data")
    public String upload(@RequestBody @Valid FileDto file) throws QiniuException {
        return service.upload(file);
    }
}
