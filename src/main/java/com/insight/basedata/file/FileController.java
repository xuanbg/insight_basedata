package com.insight.basedata.file;

import com.insight.basedata.common.dto.FileDto;
import com.insight.basedata.common.entity.UploadFile;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
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
     * 直接上传文件
     *
     * @param file 文件
     * @return Reply
     */
    @PostMapping("/v1.0/files/upload")
    public String uploadFile(@RequestHeader("loginInfo") String loginInfo, @RequestParam(required = false) MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            throw new BusinessException("文件不能为空");
        }

        return service.upload(file);
    }

    /**
     * 上传文件
     *
     * @param loginInfo 用户关键信息
     * @param file      文件DTO
     * @return 上传令牌
     */
    @PostMapping("/v1.0/files/qiniu")
    public UploadFile addFileToQiniu(@RequestHeader("loginInfo") String loginInfo, @RequestBody FileDto file) {
        var info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);

        file.setOwnerId(info.getId());
        return service.addFileToQiniu(file);
    }

    /**
     * 获取指定的文件路径
     *
     * @param id 文件ID
     * @return 文件路径
     */
    @GetMapping("/v1.0/files/{id}")
    String getFileUrl(@PathVariable Long id){
        return service.getFileUrl(id);
    }
}
