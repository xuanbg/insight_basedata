package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.ParamSearchDto;
import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.utils.pojo.base.JsonTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 选项参数DAL
 */
@Mapper
public interface ParamMapper {

    /**
     * 查询选项参数
     *
     * @param dto 选项参数查询DTO
     * @return 选项参数集合
     */
    @Results({@Result(property = "value", column = "value", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("<script>select id, `key`, `value` from icc_param where tenant_id = #{tenantId} and module_id = #{moduleId} " +
            "<if test = 'keyword != null'>and `key` = #{keyword}</if></script>")
    List<ParameterDto> getParameters(ParamSearchDto dto);

    /**
     * 获取选项参数
     *
     * @param dto 选项参数查询DTO
     * @return 选项参数DTO
     */
    @Results({@Result(property = "value", column = "value", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select id, `key`, `value` from icc_param where tenant_id = #{tenantId} and module_id = #{moduleId} and `key` = #{keyword} " +
            "and (isnull(user_id) or user_id = #{userId}) order by user_id desc limit 1;")
    ParameterDto getParameter(ParamSearchDto dto);

    /**
     * 新增参数
     *
     * @param dto 选项参数实体
     */
    @Insert("insert icc_param(tenant_id, module_id, user_id, `key`, value, creator, creator_id, created_time) " +
            "values (#{tenantId}, #{moduleId}, #{userId}, #{key}, #{value, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler}, #{creator}, #{creatorId}, #{createdTime});")
    void addParameter(Parameter dto);

    /**
     * 更新参数
     *
     * @param dto 选项参数实体
     */
    @Insert("update icc_param set value = #{value, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler} where id = #{id};")
    void updateParameter(Parameter dto);
}
