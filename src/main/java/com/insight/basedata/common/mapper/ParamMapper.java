package com.insight.basedata.common.mapper;

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
    @Select("select id, `key`, `value` from icc_param where tenant_id = #{tenantId} and module_id = #{moduleId}")
    List<ParameterDto> getParameters(Parameter dto);

    /**
     * 获取选项参数
     *
     * @param dto 选项参数查询DTO
     * @return 选项参数DTO
     */
    @Results({@Result(property = "value", column = "value", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("""
            select id, `key`, `value`
            from icc_param
            where tenant_id = #{tenantId}
              and module_id = #{moduleId}
              and `key` = #{key}
              and (user_id is null or user_id = #{userId})
            order by user_id desc
            limit 1;
            """)
    ParameterDto getParameter(Parameter dto);

    /**
     * 判断参数是否存在
     *
     * @param dto 选项参数实体
     * @return 是否存在
     */
    @Select("""
            select id
            from icc_param
            where tenant_id = #{tenantId}
              and module_id = #{moduleId}
              and user_id = #{userId}
              and `key` = #{key};
            """)
    Long parameterExists(Parameter dto);

    /**
     * 新增参数
     *
     * @param dto 选项参数实体
     */
    @Insert("insert icc_param(tenant_id, module_id, user_id, `key`, value) " +
            "values (#{tenantId}, #{moduleId}, #{userId}, #{key}, #{value, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler});")
    void addParameter(Parameter dto);

    /**
     * 更新参数
     *
     * @param dto 选项参数实体
     */
    @Insert("update icc_param set value = #{value, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler} where id = #{id};")
    void updateParameter(Parameter dto);
}
