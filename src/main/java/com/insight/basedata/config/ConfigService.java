package com.insight.basedata.config;

import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务接口
 */
public interface ConfigService {

    /**
     * 获取接口配置列表
     *
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    Reply getConfigs(String keyword, int page, int size);

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    Reply getConfig(Long id);

    /**
     * 新增接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    Reply newConfig(LoginInfo info, InterfaceConfig dto);

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置DTO
     * @return Reply
     */
    Reply editConfig(LoginInfo info, InterfaceConfig dto);

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     * @return Reply
     */
    Reply deleteConfig(LoginInfo info, Long id);

    /**
     * 加载接口配置到缓存
     *
     * @return Reply
     */
    Reply loadConfigs();

    /**
     * 获取日志列表
     *
     * @param info   用户关键信息
     * @param search 查询实体类
     * @return Reply
     */
    Reply getLogs(LoginInfo info, SearchDto search);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Reply getLog(Long id);
}
