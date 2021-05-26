package com.insight.basedata.log;

import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务控制器
 */
@CrossOrigin
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
     * @param info     用户关键信息
     * @param business 业务类型
     * @param search   查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String info, @RequestParam String business, SearchDto search) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getLogs(loginInfo, business, search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/logs/{id}")
    Reply getLog(@PathVariable Long id) {
        if (id == null) {
            return ReplyHelper.invalidParam();
        }

        return service.getLog(id);
    }
}
