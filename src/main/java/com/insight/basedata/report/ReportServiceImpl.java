package com.insight.basedata.report;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.entity.Report;
import com.insight.basedata.common.entity.Template;
import com.insight.basedata.common.mapper.ReportMapper;
import com.insight.utils.Generator;
import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.SnowflakeCreator;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.OperateType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表服务接口实现
 */
@Service
public class ReportServiceImpl implements ReportService {
    private static final String BUSINESS = "报表模板管理";
    private final SnowflakeCreator creator;
    private final ReportMapper mapper;
    private final Core core;

    /**
     * 构造方法
     *
     * @param creator 雪花算法ID生成器
     * @param mapper  ReportMapper
     * @param core    Core
     */
    public ReportServiceImpl(SnowflakeCreator creator, ReportMapper mapper, Core core) {
        this.creator = creator;
        this.mapper = mapper;
        this.core = core;
    }

    /**
     * 查询报表模板
     *
     * @param info   用户关键信息
     * @param search 查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getTemplates(LoginInfo info, Search search) {
        search.setTenantId(info.getTenantId());
        var page = PageHelper.startPage(search.getPageNum(), search.getPageSize())
                .setOrderBy(search.getOrderBy()).doSelectPage(() -> mapper.getTemplates(search));

        var total = page.getTotal();
        return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
    }

    /**
     * 获取报表模板详情
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @Override
    public Reply getTemplate(LoginInfo info, Long id) {
        Template template = mapper.getTemplate(id);
        if (template == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        if (!template.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        return ReplyHelper.success(template);
    }

    /**
     * 获取报表模板内容
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @Override
    public Reply getTemplateContent(LoginInfo info, Long id) {
        Template template = mapper.getTemplate(id);
        if (template == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        if (!template.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        return ReplyHelper.success(template.getContent());
    }

    /**
     * 导入报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    @Override
    public Reply importTemplate(LoginInfo info, Template template) {
        Long tenantId = info.getTenantId();
        String code = getCode(tenantId);

        Long id = creator.nextId(0);
        template.setId(id);
        template.setTenantId(tenantId);
        template.setCode(code);
        template.setCreator(info.getUserName());
        template.setCreatorId(info.getUserId());
        template.setCreatedTime(LocalDateTime.now());

        mapper.addTemplate(template);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, template);

        return ReplyHelper.success(id + "," + code);
    }

    /**
     * 复制报表模板
     *
     * @param info     用户关键信息
     * @param id       源模板ID
     * @param template 报表模板实体
     * @return Reply
     */
    @Override
    public Reply copyTemplate(LoginInfo info, Long id, Template template) {
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        Long tenantId = info.getTenantId();
        String code = getCode(tenantId);
        Long newId = creator.nextId(0);
        template.setId(newId);
        template.setTenantId(tenantId);
        template.setCode(code);
        template.setContent(data.getContent());
        template.setCreator(info.getUserName());
        template.setCreatorId(info.getUserId());
        template.setCreatedTime(LocalDateTime.now());

        mapper.addTemplate(template);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, newId, template);

        return ReplyHelper.success(newId + "," + code);
    }

    /**
     * 编辑报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    @Override
    public Reply editTemplate(LoginInfo info, Template template) {
        Long id = template.getId();
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权更新该数据");
        }

        mapper.updateTemplate(template);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, template);

        return ReplyHelper.success();
    }

    /**
     * 设计报表模板
     *
     * @param info     用户关键信息
     * @param template 报表模板实体
     * @return Reply
     */
    @Override
    public Reply designTemplate(LoginInfo info, Template template) {
        Long id = template.getId();
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权更新该数据");
        }

        mapper.updateTemplateContent(template);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, template);

        return ReplyHelper.success();
    }

    /**
     * 更新模板有效状态
     *
     * @param info   用户关键信息
     * @param id     模板ID
     * @param status 状态
     * @return Reply
     */
    @Override
    public Reply updateTemplateStatus(LoginInfo info, Long id, boolean status) {
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权更新该数据");
        }

        mapper.updateTemplateStatus(id, status);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, status);

        return ReplyHelper.success();
    }

    /**
     * 删除报表模板
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @Override
    public Reply deleteTemplate(LoginInfo info, Long id) {
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权删除该数据");
        }

        int count = mapper.deleteTemplate(id);
        if (count == 0) {
            return ReplyHelper.fail("无法删除使用中数据");
        }

        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);
        return ReplyHelper.success();
    }

    /**
     * 新增报表实例
     *
     * @param info   用户关键信息
     * @param report 报表实例实体类
     * @return Reply
     */
    @Override
    public Reply newReport(LoginInfo info, Report report) {
        report.setTenantId(info.getTenantId());
        report.setCreator(info.getUserName());
        report.setCreatorId(info.getUserId());

        int count = mapper.addReport(report);
        return count > 0 ? ReplyHelper.created(report) : ReplyHelper.fail("保存数据失败");
    }

    /**
     * 获取报表详情
     *
     * @param info 用户关键信息
     * @param id   报表ID
     * @return Reply
     */
    @Override
    public Reply getReport(LoginInfo info, Long id) {
        Report data = mapper.getReport(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        Byte[] bytes = data.getBytes();
        Integer[] content = new Integer[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            content[i] = bytes[i] & 0xff;
        }

        data.setBytes(null);
        data.setContent(Json.toJson(content));

        return ReplyHelper.success(data);
    }

    /**
     * 删除报表实例
     *
     * @param info 用户关键信息
     * @param id   报表实例ID
     * @return Reply
     */
    @Override
    public Reply deleteReport(LoginInfo info, Long id) {
        Report data = mapper.getReport(id);
        if (data == null) {
            return ReplyHelper.success("ID不存在,未读取数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        int count = mapper.deleteReport(id);
        return count > 0 ? ReplyHelper.success("删除数据成功") : ReplyHelper.fail("删除数据失败");
    }

    /**
     * 获取日志列表
     *
     * @param info   用户关键信息
     * @param search 查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getLogs(LoginInfo info, Search search) {
        return core.getLogs(info, BUSINESS, search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @Override
    public Reply getLog(Long id) {
        return core.getLog(id);
    }

    /**
     * 获取模板编码
     *
     * @param tenantId 租户ID
     * @return 模板编码
     */
    private String getCode(Long tenantId) {
        String group = "Common:Template:" + tenantId;
        String format = "RT-#5";
        while (true) {
            String code = Generator.newCode(format, group, false);
            int count = mapper.getCount(tenantId, code);
            if (count > 0) {
                continue;
            }

            return code;
        }
    }
}
