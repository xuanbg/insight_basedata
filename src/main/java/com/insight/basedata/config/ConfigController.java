package com.insight.basedata.config;

import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/common/config")
public class ConfigController {
    private final ConfigService service;

    /**
     * 构造方法
     *
     * @param service Service
     */
    public ConfigController(ConfigService service) {
        this.service = service;
    }

    /**
     * 获取接口配置列表
     *
     * @param search 查询条件
     * @return Reply
     */
    @GetMapping("/v1.0/configs")
    public Reply getConfigs(Search search) {
        return service.getConfigs(search);
    }

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    @GetMapping("/v1.0/configs/{id}")
    public InterfaceConfig getConfig(@PathVariable Long id) {
        return service.getConfig(id);
    }

    /**
     * 新增接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    @PostMapping("/v1.0/configs")
    public Long newConfig(@RequestHeader("loginInfo") String info, @Valid @RequestBody InterfaceConfig dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.newConfig(loginInfo, dto);
    }

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     * @param dto  接口配置
     */
    @PutMapping("/v1.0/configs/{id}")
    public void editConfig(@RequestHeader("loginInfo") String info, @PathVariable Long id, @Valid @RequestBody InterfaceConfig dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        dto.setId(id);

        service.editConfig(loginInfo, dto);
    }

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     */
    @DeleteMapping("/v1.0/configs/{id}")
    public void deleteConfig(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        service.deleteConfig(loginInfo, id);
    }

    /**
     * 加载接口配置表
     */
    @GetMapping("/v1.0/configs/load")
    public void loadConfigs() {
        service.loadConfigs();
    }

    /**
     * 获取日志列表
     *
     * @param loginInfo 用户关键信息
     * @param id        行政区划ID
     * @param search    查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/configs/{id}/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, Search search) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);

        search.setId(id);
        search.setAppId(info.getAppId());
        search.setTenantId(info.getTenantId());
        return service.getLogs(search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/configs/logs/{id}")
    public Log getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
