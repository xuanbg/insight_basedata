package com.insight.basedata.dict;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.client.TenantClient;
import com.insight.basedata.common.dto.AppListDto;
import com.insight.basedata.common.dto.DictDto;
import com.insight.basedata.common.dto.DictKeyDto;
import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.basedata.common.mapper.DictMapper;
import com.insight.utils.Json;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.OperateType;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final DictMapper mapper;
    private final TenantClient client;
    private final Core core;

    /**
     * 构造方法
     *
     * @param mapper DictMapper
     * @param client TenantClient
     * @param core   Core
     */
    public DictServiceImpl(DictMapper mapper, TenantClient client, Core core) {
        this.mapper = mapper;
        this.client = client;
        this.core = core;
    }

    /**
     * 获取字典列表
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    @Override
    public Reply getDicts(LoginInfo info, SearchDto dto) {
        List<String> ids = null;
        String tenantId = info.getTenantId();
        if (tenantId != null) {
            Reply reply = client.getApps(tenantId);
            if (!reply.getSuccess()) {
                return reply;
            }

            String json = Json.toJson(reply.getData());
            List<AppListDto> apps = Json.toList(json, AppListDto.class);
            ids = apps.stream().map(AppListDto::getId).collect(Collectors.toList());
        }

        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<DictDto> list = mapper.getDicts(ids);
        PageInfo<DictDto> pageInfo = new PageInfo<>(list);

        return ReplyHelper.success(list, pageInfo.getTotal());
    }

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @Override
    public Reply getDictKeys(LoginInfo info, String id) {
        List<DictKeyDto> list = mapper.getDictKeys(info.getTenantId(), id);

        return ReplyHelper.success(list);
    }

    /**
     * 获取指定键名的键值集合
     *
     * @param info 用户关键信息
     * @param key  字典键名
     * @return Reply
     */
    @Override
    public Reply getValues(LoginInfo info, String key) {
        List<DictKeyDto> list = mapper.getValues(info.getAppId(), info.getTenantId(), key);

        return ReplyHelper.success(list);
    }

    /**
     * 新增字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    @Override
    public Reply addDict(LoginInfo info, Dict dict) {
        String id = Util.uuid();
        dict.setId(id);
        dict.setCreator(info.getUserName());
        dict.setCreatorId(info.getUserId());
        dict.setCreatedTime(LocalDateTime.now());
        mapper.addDict(dict);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, dict);

        return ReplyHelper.success(id);
    }

    /**
     * 编辑字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    @Override
    public Reply editDict(LoginInfo info, Dict dict) {
        String id = dict.getId();
        DictDto data = mapper.getDict(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        mapper.updateDict(dict);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, dict);

        return ReplyHelper.success();
    }

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @Override
    public Reply deleteDict(LoginInfo info, String id) {
        DictDto data = mapper.getDict(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        mapper.deleteDict(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);

        return ReplyHelper.success();
    }

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @Override
    public Reply addDictKey(LoginInfo info, DictKey dictKey) {
        String id = Util.uuid();
        int count = mapper.getDictKeyCount(dictKey.getDictId(), dictKey.getCode(), dictKey.getValue());
        if (count > 0) {
            return ReplyHelper.invalidParam("已存在键值或编码");
        }

        dictKey.setId(id);
        dictKey.setTenantId(info.getTenantId());
        dictKey.setCreator(info.getUserName());
        dictKey.setCreatorId(info.getUserId());
        dictKey.setCreatedTime(LocalDateTime.now());
        mapper.addDictKey(dictKey);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, dictKey);

        return ReplyHelper.success(id);
    }

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @Override
    public Reply editDictKey(LoginInfo info, DictKey dictKey) {
        String id = dictKey.getId();
        DictKey data = mapper.getDictKey(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        if (info.getTenantId() != null && data.getTenantId() == null) {
            return ReplyHelper.fail("该数据不允许修改");
        }

        mapper.updateDictKey(dictKey);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, dictKey);

        return ReplyHelper.success();
    }

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @Override
    public Reply deleteDictKey(LoginInfo info, String id) {
        DictKey data = mapper.getDictKey(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        if (info.getTenantId() != null && data.getTenantId() == null) {
            return ReplyHelper.fail("该数据不允许删除");
        }

        mapper.deleteDictKey(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);

        return ReplyHelper.success();
    }

    /**
     * 获取日志列表
     *
     * @param info    用户关键信息
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    @Override
    public Reply getLogs(LoginInfo info, String keyword, int page, int size) {
        return core.getLogs(info, BUSINESS, keyword, page, size);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @Override
    public Reply getLog(String id) {
        return core.getLog(id);
    }
}
