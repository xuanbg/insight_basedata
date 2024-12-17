package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.redis.ZsetOps;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 选项参数服务控制器
 */
@RestController
@RequestMapping("/common/param")
public class ParamController {
    private final ParamService service;

    /**
     * 构造方法
     *
     * @param service ReportService
     */
    public ParamController(ParamService service) {
        this.service = service;
    }

    /**
     * 查询选项参数
     *
     * @param loginInfo 用户关键信息
     * @param dto       选项参数查询DTO
     * @return Reply
     */
    @GetMapping("/v1.0/params")
    public List<ParameterDto> getParameters(@RequestHeader("loginInfo") String loginInfo, Parameter dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        if (dto.getTenantId() == null) {
            dto.setTenantId(info.getTenantId());
        }

        if (dto.getModuleId() == null) {
            dto.setModuleId(info.getAppId());
        }

        return service.getParameters(dto);
    }

    /**
     * 保存选项参数
     *
     * @param loginInfo 用户关键信息
     * @param dto       选项参数
     */
    @PostMapping("/v1.0/params")
    public void saveParameter(@RequestHeader("loginInfo") String loginInfo, @RequestBody Parameter dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);

        dto.setModuleId(info.getAppId());
        dto.setTenantId(info.getTenantId());
        dto.setUserId(info.getId());
        service.saveParameter(dto);
    }

    /**
     * 获取选项参数
     *
     * @param loginInfo 用户关键信息
     * @param dto       选项参数查询DTO
     * @return Reply
     */
    @GetMapping("/v1.0/params/value")
    public ParameterDto getParameter(@RequestHeader("loginInfo") String loginInfo, Parameter dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        if (dto.getModuleId() == null) {
            dto.setModuleId(info.getAppId());
        }

        dto.setTenantId(info.getTenantId());
        dto.setUserId(info.getId());
        return service.getParameter(dto);
    }

    /**
     * 更新选项参数
     *
     * @param loginInfo 用户关键信息
     * @param dto       选项参数
     */
    @PutMapping("/v1.0/params")
    public void setParameter(@RequestHeader("loginInfo") String loginInfo, @RequestBody Parameter dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        if (dto.getTenantId() == null) {
            dto.setTenantId(info.getTenantId());
        }

        if (dto.getModuleId() == null) {
            dto.setModuleId(info.getAppId());
        }

        service.setParameter(dto);
    }

    /**
     * 按倒序获取Set中指定数量的数据
     *
     * @param key   Set KEY
     * @param count Set数据量
     * @return Set数据
     */
    @GetMapping("/v1.0/sets")
    public List<String> getSet(@RequestParam String key, @RequestParam Long count) {
        return ZsetOps.reverseRange(key, count);
    }
}
