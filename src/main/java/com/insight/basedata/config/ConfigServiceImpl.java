package com.insight.basedata.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.entity.InterfaceConfig;
import com.insight.basedata.common.mapper.ConfigMapper;
import com.insight.utils.Json;
import com.insight.utils.Redis;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.*;
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
    private final ConfigMapper mapper;

    /**
     * 构造函数
     *
     * @param mapper ConfigMapper
     */
    public ConfigServiceImpl(ConfigMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取接口配置列表
     *
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    @Override
    public Reply getConfigs(String keyword, int page, int size) {
        PageHelper.startPage(page, size);
        List<InterfaceConfig> configs = mapper.getConfigs(keyword);
        PageInfo<InterfaceConfig> pageInfo = new PageInfo<>(configs);

        return ReplyHelper.success(configs, pageInfo.getTotal());
    }

    /**
     * 获取接口配置详情
     *
     * @param id 接口配置ID
     * @return Reply
     */
    @Override
    public Reply getConfig(String id) {
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        return ReplyHelper.success(config);
    }

    /**
     * 新增接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置
     * @return Reply
     */
    @Override
    public Reply newConfig(LoginInfo info, InterfaceConfig dto) {
        String id = Util.uuid();
        dto.setId(id);
        if (dto.getNeedToken() == null) {
            dto.setNeedToken(false);
        }

        if (dto.getLogResult() == null) {
            dto.setLogResult(false);
        }

        dto.setCreatedTime(LocalDateTime.now());
        mapper.addConfig(dto);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, dto);

        Reply reply = loadConfigs();
        if (reply.getSuccess()) {
            return ReplyHelper.created(id);
        }

        reply.setData(id);
        return reply;
    }

    /**
     * 编辑接口配置
     *
     * @param info 用户关键信息
     * @param dto  接口配置DTO
     * @return Reply
     */
    @Override
    public Reply editConfig(LoginInfo info, InterfaceConfig dto) {
        String id = dto.getId();
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        mapper.editConfig(dto);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, dto);

        return loadConfigs();
    }

    /**
     * 删除接口配置
     *
     * @param info 用户关键信息
     * @param id   接口配置ID
     * @return Reply
     */
    @Override
    public Reply deleteConfig(LoginInfo info, String id) {
        InterfaceConfig config = mapper.getConfig(id);
        if (config == null) {
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        mapper.deleteConfig(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, config);

        return loadConfigs();
    }

    /**
     * 加载接口配置到缓存
     *
     * @return Reply
     */
    @Override
    public Reply loadConfigs() {
        List<InterfaceDto> configs = mapper.loadConfigs();
        if (configs == null || configs.isEmpty()) {
            return ReplyHelper.fail("读取数据失败,请重新加载");
        }

        String json = Json.toJson(configs);
        Redis.set("Config:Interface", json);

        return ReplyHelper.success();
    }
}
