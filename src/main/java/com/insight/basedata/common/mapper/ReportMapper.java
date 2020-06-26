package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.TemplateDto;
import com.insight.basedata.common.entity.Template;
import com.insight.utils.pojo.SearchDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表DAL
 */
@Mapper
public interface ReportMapper {

    /**
     * 查询报表模板
     *
     * @param dto 查询参数DTO
     * @return 报表模板集合
     */
    @Select("<script>select id, `code`, `name`, remark, is_invalid from icc_template where tenant_id = #{tenantId} " +
            "<if test = 'keyword != null'>and (`code` = #{keyword} or `name` like concat('%',#{keyword},'%')) </if>" +
            "order by created_time</script>")
    List<TemplateDto> getTemplates(SearchDto dto);

    /**
     * 获取报表模板详情
     *
     * @param id 报表模板ID
     * @return 报表模板实体
     */
    @Select("select * from icc_template where id = #{id};")
    Template getTemplate(String id);

    /**
     * 获取指定租户下指定编码的数量
     *
     * @param tenantId 租户ID
     * @param code     编码
     * @return 数量
     */
    @Select("select count(*) from icc_template where tenant_id = #{tenantId} and code = #{code}")
    int getCount(@Param("tenantId") String tenantId, @Param("code") String code);

    /**
     * 新增报表模板
     *
     * @param template 报表模板实体
     */
    @Insert("insert icc_template(id, tenant_id, `code`, `name`, content, remark, creator, creator_id, created_time) " +
            "values (#{id}, #{tenantId}, #{code}, #{name}, #{content}, #{remark}, #{creator}, #{creatorId}, #{createdTime});")
    void addTemplate(Template template);

    /**
     * 更新报表模板数据
     *
     * @param template 报表模板实体
     */
    @Update("update icc_template set `name` = #{name}, remark = #{remark} where id = #{id};")
    void updateTemplate(Template template);

    /**
     * 更新报表模板数据
     *
     * @param template 报表模板实体
     */
    @Update("update icc_template set content = #{content} where id = #{id};")
    void updateTemplateContent(Template template);

    /**
     * 更新报表模板有效状态
     *
     * @param id     报表模板ID
     * @param status 有效状态
     */
    @Update("update icc_template set is_invalid = #{status} where id = #{id};")
    void updateTemplateStatus(@Param("id") String id, @Param("status") boolean status);

    /**
     * 删除报表模板数据
     *
     * @param id 报表模板ID
     * @return 受影响行数
     */
    @Delete("delete from icc_template where id = #{id} and is_invalid = 1;")
    int deleteTemplate(String id);
}
