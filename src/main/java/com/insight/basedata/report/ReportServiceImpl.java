package com.insight.basedata.report;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.dto.TemplateDto;
import com.insight.basedata.common.entity.Template;
import com.insight.basedata.common.mapper.ReportMapper;
import com.insight.utils.Generator;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.OperateType;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表服务接口实现
 */
@Service
public class ReportServiceImpl implements ReportService {
    private static final String BUSINESS = "报表模板管理";
    private final ReportMapper mapper;
    private final Core core;

    /**
     * 构造方法
     *
     * @param mapper ReportMapper
     * @param core   Core
     */
    public ReportServiceImpl(ReportMapper mapper, Core core) {
        this.mapper = mapper;
        this.core = core;
    }

    /**
     * 查询报表模板
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getTemplates(LoginInfo info, SearchDto dto) {
        dto.setTenantId(info.getTenantId());
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<TemplateDto> templates = mapper.getTemplates(dto);
        PageInfo<TemplateDto> pageInfo = new PageInfo<>(templates);

        return ReplyHelper.success(templates, pageInfo.getTotal());
    }

    /**
     * 获取报表模板详情
     *
     * @param info 用户关键信息
     * @param id   模板ID
     * @return Reply
     */
    @Override
    public Reply getTemplate(LoginInfo info, String id) {
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
    public Reply getTemplateContent(LoginInfo info, String id) {
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
        String tenantId = info.getTenantId();
        String code = getCode(tenantId);

        String id = Util.uuid();
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
    public Reply copyTemplate(LoginInfo info, String id, Template template) {
        Template data = mapper.getTemplate(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        if (!data.getTenantId().equals(info.getTenantId())) {
            return ReplyHelper.fail("您无权读取该数据");
        }

        String tenantId = info.getTenantId();
        String code = getCode(tenantId);
        String newId = Util.uuid();
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
        String id = template.getId();
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
        String id = template.getId();
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
    public Reply updateTemplateStatus(LoginInfo info, String id, boolean status) {
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
    public Reply deleteTemplate(LoginInfo info, String id) {
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
     * 获取日志列表
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getLogs(LoginInfo info, SearchDto dto) {
        return core.getLogs(info, BUSINESS, dto.getKeyword(), dto.getPage(), dto.getSize());
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @Override
    public Reply getLog(String id) {
        return core.getLog(id);
    }

    /**
     * 获取模板编码
     *
     * @param tenantId 租户ID
     * @return 模板编码
     */
    private String getCode(String tenantId) {
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
