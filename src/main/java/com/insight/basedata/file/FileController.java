package com.insight.basedata.file;

import com.insight.utils.pojo.base.BusinessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件管理服务控制器
 */
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
    public String uploadFile(@RequestParam(required = false) MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            throw new BusinessException("文件不能为空");
        }

        return service.upload(file);
    }
}
