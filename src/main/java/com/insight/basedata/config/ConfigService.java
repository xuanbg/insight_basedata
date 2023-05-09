package com.insight.basedata.config;

import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务接口
 */
public interface ConfigService {

    /**
     * 获取接口配置列表
     *
     * @param search 查询条件
     * @return Reply
     */
    Reply getConfigs(Search search);

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    InterfaceConfig getConfig(Long id);

    /**
     * 新增接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    Long newConfig(LoginInfo info, InterfaceConfig dto);

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置DTO
     */
    void editConfig(LoginInfo info, InterfaceConfig dto);

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     */
    void deleteConfig(LoginInfo info, Long id);

    /**
     * 加载接口配置到缓存
     */
    void loadConfigs();

    /**
     * 获取日志列表
     *
     * @param search 查询实体类
     * @return Reply
     */
    Reply getLogs(Search search);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    LogInfo getLog(Long id);
}
