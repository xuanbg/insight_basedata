package com.insight.basedata.log;

import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
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
     * @param keyword  查询关键词
     * @param page     分页页码
     * @param size     每页记录数
     * @return Reply
     */
    @GetMapping("/v1.0/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String info, @RequestParam String business, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getLogs(loginInfo, business, keyword, page, size);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/logs/{id}")
    Reply getLog(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ReplyHelper.invalidParam();
        }

        return service.getLog(id);
    }
}
