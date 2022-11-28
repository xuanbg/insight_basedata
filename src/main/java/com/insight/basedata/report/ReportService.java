package com.insight.basedata.report;

import com.insight.basedata.common.entity.Report;
import com.insight.basedata.common.entity.Template;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 报表服务接口
 */
public interface ReportService {

    /**
     * 查询报表模板
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    Reply getTemplates(LoginInfo info, Search dto);

    /**
     * 获取报表模板详情
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    Template getTemplate(LoginInfo info, Long id);

    /**
     * 获取报表模板内容
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    String getTemplateContent(LoginInfo info, Long id);

    /**
     * 导入报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    String importTemplate(LoginInfo info, Template template);

    /**
     * 复制报表模板
     *
     * @param info     用户关键信息
     * @param id       源模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    String copyTemplate(LoginInfo info, Long id, Template template);

    /**
     * 编辑报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     */
    void editTemplate(LoginInfo info, Template template);

    /**
     * 设计报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     */
    void designTemplate(LoginInfo info, Template template);

    /**
     * 更新模板有效状态
     *
     * @param info   用户关键信息
     * @param id     模板ID
     * @param status 状态
     */
    void updateTemplateStatus(LoginInfo info, Long id, boolean status);

    /**
     * 删除报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     */
    void deleteTemplate(LoginInfo info, Long id);

    /**
     * 新增报表实例
     *
     * @param info   用户关键信息
     * @param report 报表实例实体类
     */
    void newReport(LoginInfo info, Report report);

    /**
     * 获取报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    Report getReport(LoginInfo info, Long id);

    /**
     * 删除报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     */
    void deleteReport(LoginInfo info, Long id);

    /**
     * 获取日志列表
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    Reply getLogs(LoginInfo info, Search dto);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Log getLog(Long id);
}
