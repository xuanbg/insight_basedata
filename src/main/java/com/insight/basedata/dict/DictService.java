package com.insight.basedata.dict;

import com.insight.basedata.common.dto.DictKeyDto;
import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.basedata.common.entity.DictValue;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典服务接口
 */
public interface DictService {

    /**
     * 获取字典列表
     *
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    Reply getDicts(LoginInfo info, Search dto);

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    List<DictKeyDto> getDictKeys(LoginInfo info, Long id);

    /**
     * 获取指定键名的键值集合
     *
     * @param tenantId 租户ID
     * @param key      字典KEY
     * @return Reply
     */
    List<DictValue> getValues(Long tenantId, String key);

    /**
     * 新增字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    Long addDict(LoginInfo info, Dict dict);

    /**
     * 编辑字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     */
    void editDict(LoginInfo info, Dict dict);

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     */
    void deleteDict(LoginInfo info, Long id);

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    Long addDictKey(LoginInfo info, DictKey dictKey);

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     */
    void editDictKey(LoginInfo info, DictKey dictKey);

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典ID
     */
    void deleteDictKey(LoginInfo info, Long id);

    /**
     * 获取日志列表
     *
     * @param info   用户关键信息
     * @param search 查询实体类
     * @return Reply
     */
    Reply getLogs(LoginInfo info, Search search);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Log getLog(Long id);
}
