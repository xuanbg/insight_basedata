package com.insight.basedata.common.mapper;

import com.insight.utils.common.JsonTypeHandler;
import com.insight.utils.pojo.Log;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理DAL
 */
@Mapper
public interface LogMapper {

    /**
     * 获取操作日志列表
     *
     * @param appId    应用ID
     * @param tenantId 租户ID
     * @param business 业务类型
     * @param key      查询关键词
     * @return 操作日志列表
     */
    @Select("<script>select id, type, business, business_id, creator, creator_id, created_time from icl_operate_log " +
            "where app_id = #{appId} and business = #{business} " +
            "<if test = 'tenantId != null'>and tenant_id = #{tenantId} </if>" +
            "<if test = 'tenantId == null'>and isnull(tenant_id) </if>" +
            "<if test = 'key!=null'>and (business_id = #{key} or creator = #{key})</if>" +
            "order by created_time</script>")
    List<Log> getLogs(@Param("appId") Long appId, @Param("tenantId") Long tenantId, @Param("business") String business, @Param("key") String key);

    /**
     * 获取操作日志列表
     *
     * @param id 日志ID
     * @return 操作日志列表
     */
    @Results({@Result(property = "content", column = "content", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select * from icl_operate_log where id = #{id};")
    Log getLog(Long id);

    /**
     * 记录操作日志
     *
     * @param log 日志DTO
     */
    @Insert("insert icl_operate_log(app_id, tenant_id, type, business, business_id, content, creator, creator_id, created_time) values " +
            "(#{appId}, #{tenantId}, #{type}, #{business}, #{businessId}, #{content, typeHandler = com.insight.utils.common.JsonTypeHandler}, " +
            "#{creator}, #{creatorId}, #{createdTime});")
    void addLog(Log log);
}
