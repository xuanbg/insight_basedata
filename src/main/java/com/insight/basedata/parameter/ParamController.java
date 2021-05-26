package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParamSearchDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.utils.Json;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 选项参数服务控制器
 */
@CrossOrigin
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
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    @GetMapping("/v1.0/params")
    public Reply getParameters(@RequestHeader("loginInfo") String info, ParamSearchDto dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getParameters(loginInfo, dto);
    }

    /**
     * 获取选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    @GetMapping("/v1.0/params/value")
    public Reply getParameter(@RequestHeader("loginInfo") String info, ParamSearchDto dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getParameter(loginInfo, dto);
    }

    /**
     * 更新选项参数
     *
     * @param info       用户关键信息
     * @param parameters 选项参数实体集合
     * @return Reply
     */
    @PutMapping("/v1.0/params")
    public Reply setParameter(@RequestHeader("loginInfo") String info, @RequestBody List<Parameter> parameters) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.setParameter(loginInfo, parameters);
    }
}
