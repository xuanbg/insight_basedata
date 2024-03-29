package com.insight.basedata.log;

import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import org.springframework.web.bind.annotation.*;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务控制器
 */
@RestController
@RequestMapping("/common/log")
public class LogController {
    private final LogService service;

    /**
     * 构造方法
     *
     * @param service LogService
     */
    public LogController(LogService service) {
        this.service = service;
    }

    /**
     * 获取日志列表
     *
     * @param loginInfo 用户关键信息
     * @param search    查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String loginInfo, Search search) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);

        search.setAppId(info.getAppId());
        search.setTenantId(info.getTenantId());
        return service.getLogs(search);
    }

    /**
     * 打印日志
     * @param log 日志内容
     */
    @PostMapping("/v1.0/logs")
    public void log(@RequestBody String log){
        throw new BusinessException(log);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/logs/{id}")
    LogInfo getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
