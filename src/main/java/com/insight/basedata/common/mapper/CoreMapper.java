package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.FileDto;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.pojo.base.JsonTypeHandler;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.base.TreeBase;
import com.insight.utils.pojo.message.Log;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理DAL
 */
@Mapper
public interface CoreMapper {

    /**
     * 按文件哈希值查找文件
     *
     * @param hash 文件哈希值
     * @return 文件ID
     */
    @Select("select * from icf_file where hash = #{hash};")
    FileDto getFileByHash(String hash);

    /**
     * 获取指定名称的路径ID
     *
     * @param ownerId 用户ID
     * @return 路径ID
     */
    @Select("""
            select id, parent_id, name
            from icf_file
            where owner_id = #{ownerId}
              and type = 0
              and invalid = 0;
            """)
    List<TreeBase> getFolders(Long ownerId);

    /**
     * 新增文件记录
     *
     * @param file 文件DTO
     * @return 上传令牌
     */
    @Select("""
            insert icf_file (id, parent_id, type, name, ext, domain, url, hash, size, owner_id, created_time) values
            (#{id}, #{parentId}, #{type}, #{name}, #{ext}, #{domain}, #{url}, #{hash}, #{size}, #{ownerId}, now());
            """)
    String addFile(FileDto file);

    /**
     * 获取文件URL
     *
     * @param id 文件ID
     * @return 文件URL
     */
    @Select("select url from icf_file where id = #{id};")
    String getFile(Long id);

    /**
     * 获取操作日志列表
     *
     * @param search 查询实体类
     * @return 操作日志列表
     */
    @Results({@Result(property = "content", column = "content", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("""
            <script>select * from icl_operate_log where business_id = #{id} and app_id = #{appId} and business = #{code}
            <if test = 'tenantId != null'>and tenant_id = #{tenantId}</if>
            <if test = 'tenantId == null'>and isnull(tenant_id)</if>
            <if test = 'keyword!=null'>and creator = #{keyword}</if>
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
