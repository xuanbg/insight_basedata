package com.insight.basedata.report;

import com.insight.basedata.common.entity.Report;
import com.insight.basedata.common.entity.Template;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;

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
    Reply getTemplates(LoginInfo info, SearchDto dto);

    /**
     * 获取报表模板详情
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    Reply getTemplate(LoginInfo info, Long id);

    /**
     * 获取报表模板内容
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    Reply getTemplateContent(LoginInfo info, Long id);

    /**
     * 导入报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    Reply importTemplate(LoginInfo info, Template template);

    /**
     * 复制报表模板
     *
     * @param info     用户关键信息
     * @param id       源模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    Reply copyTemplate(LoginInfo info, Long id, Template template);

    /**
     * 编辑报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    Reply editTemplate(LoginInfo info, Template template);

    /**
     * 设计报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    Reply designTemplate(LoginInfo info, Template template);

    /**
     * 更新模板有效状态
     *
     * @param info   用户关键信息
     * @param id     模板ID
     * @param status 状态
     * @return Reply
     */
    Reply updateTemplateStatus(LoginInfo info, Long id, boolean status);

    /**
     * 删除报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    Reply deleteTemplate(LoginInfo info, Long id);

    /**
     * 新增报表实例
     *
     * @param info   用户关键信息
     * @param report 报表实例实体类
     * @return Reply
     */
    Reply newReport(LoginInfo info, Report report);

    /**
     * 获取报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    Reply getReport(LoginInfo info, Long id);

    /**
     * 删除报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    Reply deleteReport(LoginInfo info, Long id);

    /**
     * 获取日志列表
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    Reply getLogs(LoginInfo info, SearchDto dto);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Reply getLog(Long id);
}
