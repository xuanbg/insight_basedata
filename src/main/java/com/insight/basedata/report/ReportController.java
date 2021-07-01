package com.insight.basedata.report;

import com.insight.basedata.common.entity.Report;
import com.insight.basedata.common.entity.Template;
import com.insight.utils.Json;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表服务控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/common/report")
public class ReportController {
    private final ReportService service;

    /**
     * 构造方法
     *
     * @param service ReportService
     */
    public ReportController(ReportService service) {
        this.service = service;
    }

    /**
     * 查询报表模板
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/templates")
    public Reply getTemplates(@RequestHeader("loginInfo") String info, SearchDto dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getTemplates(loginInfo, dto);
    }

    /**
     * 获取报表模板详情
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @GetMapping("/v1.0/templates/{id}")
    public Reply getTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getTemplate(loginInfo, id);
    }

    /**
     * 获取报表模板内容
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @GetMapping("/v1.0/templates/{id}/content")
    public Reply getTemplateContent(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getTemplateContent(loginInfo, id);
    }

    /**
     * 导入报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    @PostMapping("/v1.0/templates")
    public Reply importTemplate(@RequestHeader("loginInfo") String info, @RequestBody Template template) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.importTemplate(loginInfo, template);
    }

    /**
     * 复制报表模板
     *
     * @param info     用户关键信息
     * @param id       源模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    @PostMapping("/v1.0/templates/{id}")
    public Reply copyTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.copyTemplate(loginInfo, id, template);
    }

    /**
     * 编辑报表模板
     *
     * @param info     用户关键信息
     * @param id       模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    @PutMapping("/v1.0/templates/{id}")
    public Reply editTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        template.setId(id);

        return service.editTemplate(loginInfo, template);
    }

    /**
     * 设计报表模板
     *
     * @param info     用户关键信息
     * @param id       模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    @PutMapping("/v1.0/templates/{id}/content")
    public Reply designTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        template.setId(id);

        return service.designTemplate(loginInfo, template);
    }

    /**
     * 禁用报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @PutMapping("/v1.0/templates/{id}/disable")
    public Reply disableTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.updateTemplateStatus(loginInfo, id, true);
    }

    /**
     * 启用报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @PutMapping("/v1.0/templates/{id}/enable")
    public Reply enableTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.updateTemplateStatus(loginInfo, id, false);
    }

    /**
     * 删除报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/templates/{id}")
    public Reply deleteTemplate(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteTemplate(loginInfo, id);
    }

    /**
     * 新增报表实例
     *
     * @param info   用户关键信息
     * @param report 报表实例实体
     * @return Reply
     */
    @PostMapping("/v1.0/reports")
    public Reply importTemplate(@RequestHeader("loginInfo") String info, @RequestBody Report report) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.newReport(loginInfo, report);
    }

    /**
     * 获取报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    @GetMapping("/v1.0/reports/{id}")
    public Reply getReport(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getReport(loginInfo, id);
    }

    /**
     * 删除报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/reports/{id}")
    public Reply deleteReport(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteReport(loginInfo, id);
    }

    /**
     * 获取日志列表
     *
     * @param info   用户关键信息
     * @param search 查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/templates/logs")
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
    @GetMapping("/v1.0/templates/logs/{id}")
    public Reply getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
