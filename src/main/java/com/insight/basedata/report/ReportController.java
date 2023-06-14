package com.insight.basedata.report;

import com.insight.basedata.common.entity.LogInfo;
import com.insight.basedata.common.entity.Report;
import com.insight.basedata.common.entity.Template;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import org.springframework.web.bind.annotation.*;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表服务控制器
 */
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
     * @param loginInfo 用户关键信息
     * @param dto       查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/templates")
    public Reply getTemplates(@RequestHeader("loginInfo") String loginInfo, Search dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getTemplates(info, dto);
    }

    /**
     * 获取报表模板详情
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     * @return Reply
     */
    @GetMapping("/v1.0/templates/{id}")
    public Template getTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getTemplate(info, id);
    }

    /**
     * 获取报表模板内容
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     * @return Reply
     */
    @GetMapping("/v1.0/templates/{id}/content")
    public String getTemplateContent(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getTemplateContent(info, id);
    }

    /**
     * 导入报表模板
     *
     * @param loginInfo 用户关键信息
     * @param template  报表模板实体
     * @return Reply
     */
    @PostMapping("/v1.0/templates")
    public String importTemplate(@RequestHeader("loginInfo") String loginInfo, @RequestBody Template template) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.importTemplate(info, template);
    }

    /**
     * 复制报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        源模板ID
     * @param template  报表模板实体
     * @return Reply
     */
    @PostMapping("/v1.0/templates/{id}")
    public String copyTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.copyTemplate(info, id, template);
    }

    /**
     * 编辑报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     * @param template  报表模板实体
     */
    @PutMapping("/v1.0/templates/{id}")
    public void editTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        template.setId(id);

        service.editTemplate(info, template);
    }

    /**
     * 设计报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     * @param template  报表模板实体
     */
    @PutMapping("/v1.0/templates/{id}/content")
    public void designTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @RequestBody Template template) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        template.setId(id);

        service.designTemplate(info, template);
    }

    /**
     * 禁用报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     */
    @PutMapping("/v1.0/templates/{id}/disable")
    public void disableTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.updateTemplateStatus(info, id, true);
    }

    /**
     * 启用报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     */
    @PutMapping("/v1.0/templates/{id}/enable")
    public void enableTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.updateTemplateStatus(info, id, false);
    }

    /**
     * 删除报表模板
     *
     * @param loginInfo 用户关键信息
     * @param id        模板ID
     */
    @DeleteMapping("/v1.0/templates/{id}")
    public void deleteTemplate(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.deleteTemplate(info, id);
    }

    /**
     * 新增报表实例
     *
     * @param loginInfo 用户关键信息
     * @param report    报表实例实体
     */
    @PostMapping("/v1.0/reports")
    public void importTemplate(@RequestHeader("loginInfo") String loginInfo, @RequestBody Report report) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.newReport(info, report);
    }

    /**
     * 获取报表实例
     *
     * @param loginInfo 用户关键信息
     * @param id        报表实例ID
     * @return Reply
     */
    @GetMapping("/v1.0/reports/{id}")
    public Report getReport(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getReport(info, id);
    }

    /**
     * 删除报表实例
     *
     * @param loginInfo 用户关键信息
     * @param id        报表实例ID
     */
    @DeleteMapping("/v1.0/reports/{id}")
    public void deleteReport(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.deleteReport(info, id);
    }

    /**
     * 获取日志列表
     *
     * @param loginInfo 用户关键信息
     * @param id        行政区划ID
     * @param search    查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/templates/{id}/logs")
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
    @GetMapping("/v1.0/templates/logs/{id}")
    public LogInfo getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
