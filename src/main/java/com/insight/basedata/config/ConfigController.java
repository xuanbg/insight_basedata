package com.insight.basedata.config;

import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    @GetMapping("/v1.0/configs")
    public Reply getConfigs(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return service.getConfigs(keyword, page, size);
    }

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    @GetMapping("/v1.0/configs/{id}")
    Reply getConfig(@PathVariable Long id) {
        if (id == null) {
            return ReplyHelper.invalidParam();
        }

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
    public Reply newConfig(@RequestHeader("loginInfo") String info, @Valid @RequestBody InterfaceConfig dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.newConfig(loginInfo, dto);
    }

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    @PutMapping("/v1.0/configs")
    public Reply editConfig(@RequestHeader("loginInfo") String info, @Valid @RequestBody InterfaceConfig dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.editConfig(loginInfo, dto);
    }

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/configs")
    Reply deleteConfig(@RequestHeader("loginInfo") String info, @RequestBody Long id) {
        if (id == null) {
            return ReplyHelper.invalidParam();
        }

        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        return service.deleteConfig(loginInfo, id);
    }

    /**
     * 加载接口配置表
     *
     * @return Reply
     */
    @GetMapping("/v1.0/configs/load")
    public Reply loadConfigs() {
        return service.loadConfigs();
    }

    /**
     * 获取日志列表
     *
     * @param info    用户关键信息
     * @param search 查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/configs/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String info, SearchDto search) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getLogs(loginInfo, search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/configs/logs/{id}")
    Reply getLog(@PathVariable Long id) {
        if (id == null) {
            return ReplyHelper.invalidParam();
        }

        return service.getLog(id);
    }
}
