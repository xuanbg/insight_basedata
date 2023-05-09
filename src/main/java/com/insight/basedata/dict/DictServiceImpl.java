package com.insight.basedata.dict;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.client.TenantClient;
import com.insight.basedata.common.dto.AppListDto;
import com.insight.basedata.common.dto.DictDto;
import com.insight.basedata.common.dto.DictKeyDto;
import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.basedata.common.entity.DictValue;
import com.insight.basedata.common.mapper.DictMapper;
import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.SnowflakeCreator;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;
import com.insight.utils.pojo.message.OperateType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典服务接口实现
 */
@Service
public class DictServiceImpl implements DictService {
    private static final String BUSINESS = "字典数据管理";
    private final SnowflakeCreator creator;
    private final DictMapper mapper;
    private final TenantClient client;
    private final Core core;

    /**
     * 构造方法
     *
     * @param creator 雪花算法ID生成器
     * @param mapper  DictMapper
     * @param client  TenantClient
     * @param core    Core
     */
    public DictServiceImpl(SnowflakeCreator creator, DictMapper mapper, TenantClient client, Core core) {
        this.creator = creator;
        this.mapper = mapper;
        this.client = client;
        this.core = core;
    }

    /**
     * 获取字典列表
     *
     * @param info   用户关键信息
     * @param search 查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getDicts(LoginInfo info, Search search) {
        List<Long> ids = new ArrayList<>();
        if (search.getAppId() != null) {
            ids.add(search.getAppId());
        }

        Long tenantId = info.getTenantId();
        if (ids.isEmpty() && tenantId != null) {
            Reply reply = client.getApps(tenantId);
            if (!reply.getSuccess()) {
                return reply;
            }

            String json = Json.toJson(reply.getData());
            List<AppListDto> apps = Json.toList(json, AppListDto.class);
            ids = apps.stream().map(AppListDto::getId).collect(Collectors.toList());
        }

        search.setLongSet(ids);
        var page = PageHelper.startPage(search.getPageNum(), search.getPageSize())
                .setOrderBy(search.getOrderBy()).doSelectPage(() -> mapper.getDicts(search));

        var total = page.getTotal();
        return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
    }

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @Override
    public List<DictKeyDto> getDictKeys(LoginInfo info, Long id) {
        return mapper.getDictKeys(info.getTenantId(), id);
    }

    /**
     * 获取指定键名的键值集合
     *
     * @param tenantId 租户ID
     * @param key      字典KEY
     * @return Reply
     */
    @Override
    public List<DictValue> getValues(Long tenantId, String key) {
        return mapper.getValues(tenantId, key);
    }

    /**
     * 新增字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    @Override
    public Long addDict(LoginInfo info, Dict dict) {
        Long id = creator.nextId(2);
        dict.setId(id);
        dict.setCreator(info.getName());
        dict.setCreatorId(info.getId());
        dict.setCreatedTime(LocalDateTime.now());
        mapper.addDict(dict);
        LogClient.writeLog(info, BUSINESS, OperateType.NEW, id, dict);

        return id;
    }

    /**
     * 编辑字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     */
    @Override
    public void editDict(LoginInfo info, Dict dict) {
        Long id = dict.getId();
        DictDto data = mapper.getDict(id);
        if (data == null) {
            throw new BusinessException("ID不存在,未更新数据");
        }

        mapper.updateDict(dict);
        LogClient.writeLog(info, BUSINESS, OperateType.EDIT, id, dict);
    }

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     */
    @Override
    public void deleteDict(LoginInfo info, Long id) {
        DictDto data = mapper.getDict(id);
        if (data == null) {
            throw new BusinessException("ID不存在,未删除数据");
        }

        mapper.deleteDict(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);
    }

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @Override
    public Long addDictKey(LoginInfo info, DictKey dictKey) {
        Long id = creator.nextId(3);
        int count = mapper.getDictKeyCount(dictKey.getDictId(), dictKey.getCode(), dictKey.getValue());
        if (count > 0) {
            throw new BusinessException("已存在键值或编码");
        }

        dictKey.setId(id);
        dictKey.setTenantId(info.getTenantId());
        dictKey.setCreator(info.getName());
        dictKey.setCreatorId(info.getId());
        dictKey.setCreatedTime(LocalDateTime.now());
        mapper.addDictKey(dictKey);
        LogClient.writeLog(info, BUSINESS, OperateType.NEW, id, dictKey);

        return id;
    }

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     */
    @Override
    public void editDictKey(LoginInfo info, DictKey dictKey) {
        Long id = dictKey.getId();
        DictKey data = mapper.getDictKey(id);
        if (data == null) {
            throw new BusinessException("ID不存在,未更新数据");
        }

        if (info.getTenantId() != null && data.getTenantId() == null) {
            throw new BusinessException("该数据不允许修改");
        }

        mapper.updateDictKey(dictKey);
        LogClient.writeLog(info, BUSINESS, OperateType.EDIT, id, dictKey);
    }

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典ID
     */
    @Override
    public void deleteDictKey(LoginInfo info, Long id) {
        DictKey data = mapper.getDictKey(id);
        if (data == null) {
            throw new BusinessException("ID不存在,未删除数据");
        }

        if (info.getTenantId() != null && data.getTenantId() == null) {
            throw new BusinessException("该数据不允许删除");
        }

        mapper.deleteDictKey(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);
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
