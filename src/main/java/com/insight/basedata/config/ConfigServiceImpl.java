package com.insight.basedata.config;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.basedata.common.mapper.ConfigMapper;
import com.insight.utils.Json;
import com.insight.utils.redis.Redis;
import com.insight.utils.ReplyHelper;
import com.insight.utils.SnowflakeCreator;
import com.insight.utils.pojo.auth.InterfaceDto;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;
import com.insight.utils.pojo.message.OperateType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    private static final String BUSINESS = "接口配置管理";
    private final SnowflakeCreator creator;
    private final ConfigMapper mapper;
    private final Core core;

    /**
     * 构造函数
     *
     * @param creator 雪花算法ID生成器
     * @param mapper  ConfigMapper
     * @param core    Core
     */
    public ConfigServiceImpl(SnowflakeCreator creator, ConfigMapper mapper, Core core) {
        this.creator = creator;
        this.mapper = mapper;
        this.core = core;
    }

    /**
     * 获取接口配置列表
     *
     * @param search 查询条件
     * @return Reply
     */
    @Override
    public Reply getConfigs(Search search) {
        var page = PageHelper.startPage(search.getPageNum(), search.getPageSize())
                .setOrderBy(search.getOrderBy()).doSelectPage(() -> mapper.getConfigs(search));

        var total = page.getTotal();
        return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
    }

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    @Override
    public InterfaceConfig getConfig(Long id) {
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            throw new BusinessException("ID不存在,未读取数据");
        }

        return config;
    }

    /**
     * 新增接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    @Override
    public Long newConfig(LoginInfo info, InterfaceConfig dto) {
        Long id = creator.nextId(4);
        dto.setId(id);
        dto.setCreatedTime(LocalDateTime.now());

        mapper.addConfig(dto);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, dto);

        loadConfigs();
        return id;
    }

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置DTO
     */
    @Override
    public void editConfig(LoginInfo info, InterfaceConfig dto) {
        Long id = dto.getId();
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            throw new BusinessException("ID不存在,未更新数据");
        }

        mapper.editConfig(dto);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, dto);
        loadConfigs();
    }

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     */
    @Override
    public void deleteConfig(LoginInfo info, Long id) {
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            throw new BusinessException("ID不存在,未删除数据");
        }

        mapper.deleteConfig(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, config);
        loadConfigs();
    }

    /**
     * 加载接口配置到缓存
     */
    @Override
    public void loadConfigs() {
        List<InterfaceDto> configs = mapper.loadConfigs();
        if (configs == null || configs.isEmpty()) {
            throw new BusinessException("读取数据失败,请重新加载");
        }

        String json = Json.toJson(configs);
        Redis.set("Config:Interface", json);
    }

    /**
     * 获取日志列表
     *
     * @param search 查询实体类
     * @return Reply
     */
    @Override
    public Reply getLogs(Search search) {
        search.setCode(BUSINESS);
        return core.getLogs(search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @Override
    public Log getLog(Long id) {
        return core.getLog(id);
    }
}
