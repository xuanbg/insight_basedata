package com.insight.basedata.common.mapper;

import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.pojo.base.JsonTypeHandler;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;
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
     * @param search   查询实体类
     * @return 操作日志列表
     */
    @Results({@Result(property = "content", column = "content", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("""
            <script>select * from icl_operate_log where business_id = #{id} and app_id = #{appId} and business = #{code}
            <if test = 'tenantId != null'>and tenant_id = #{tenantId}</if>
            <if test = 'tenantId == null'>and isnull(tenant_id)</if>
            <if test = 'keyword!=null'>and (business_id = #{keyword} or creator = #{keyword})</if>
            </script>
            """)
    List<LogInfo> getLogs(Search search);

    /**
     * 获取操作日志列表
     *
     * @param id 日志ID
     * @return 操作日志列表
     */
    @Results({@Result(property = "content", column = "content", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select * from icl_operate_log where id = #{id};")
    LogInfo getLog(Long id);

    /**
     * 记录操作日志
     *
     * @param log 日志DTO
     */
    @Insert("insert icl_operate_log(app_id, tenant_id, type, business, business_id, content, creator, creator_id, created_time) values " +
            "(#{appId}, #{tenantId}, #{type}, #{business}, #{businessId}, #{content, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler}, " +
            "#{creator}, #{creatorId}, #{createdTime});")
    void addLog(Log log);
}
