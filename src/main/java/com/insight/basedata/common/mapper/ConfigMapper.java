package com.insight.basedata.common.mapper;

import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.utils.pojo.auth.InterfaceDto;
import com.insight.utils.pojo.base.Search;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理DAL
 */
@Mapper
public interface ConfigMapper {

    /**
     * 获取接口配置
     *
     * @param search 查询条件
     * @return 接口配置表
     */
    @Select("<script>select * from ici_interface " +
            "<if test = 'keyword!=null'>where name like concat('%',#{keyword},'%') or url like concat('%',#{keyword},'%') or auth_code = #{keyword}</if>" +
            "</script>")
    List<InterfaceConfig> getConfigs(Search search);

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return 接口配置详情
     */
    @Select("select * from ici_interface where id = #{id};")
    InterfaceConfig getConfig(Long id);

    /**
     * 在数据库中写入接口配置
     *
     * @param config 接口配置
     */
    @Insert("insert ici_interface(id, name, method, url, auth_code, limit_gap, limit_cycle, limit_max, message, remark, need_token, is_verify, is_limit, is_log_result, created_time) values " +
            "(#{id}, #{name}, #{method}, #{url}, #{authCode}, #{limitGap}, #{limitCycle}, #{limitMax}, #{message}, #{remark}, #{needToken}, #{isVerify}, #{isLimit}, #{isLogResult}, #{createdTime});")
    void addConfig(InterfaceConfig config);

    /**
     * 在数据库中写入接口配置
     *
     * @param config 接口配置
     */
    @Update("update ici_interface set name = #{name}, method = #{method}, url = #{url}, auth_code = #{authCode}, " +
            "limit_gap = #{limitGap}, limit_cycle = #{limitCycle}, limit_max = #{limitMax}, message = #{message}, " +
            "remark = #{remark}, need_token = #{needToken}, is_verify = #{isVerify}, is_limit = #{isLimit}, is_log_result = #{isLogResult} where id = #{id};")
    void editConfig(InterfaceConfig config);

    /**
     * 删除接口配置
     *
     * @param id 接口配置ID
     */
    @Delete("delete from ici_interface where id = #{id};")
    void deleteConfig(Long id);

    /**
     * 获取接口配置
     *
     * @return 接口配置表
     */
    @Select("select method, url, auth_code, limit_gap, limit_cycle, limit_max, message, need_token, is_verify, is_limit, is_log_result from ici_interface;")
    List<InterfaceDto> loadConfigs();
}
