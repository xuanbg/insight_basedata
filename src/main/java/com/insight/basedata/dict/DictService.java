package com.insight.basedata.dict;

import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;

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
    Reply getDictKeys(LoginInfo info, Long id);

    /**
     * 获取指定键名的键值集合
     *
     * @param info 用户关键信息
     * @param key  字典键名
     * @return Reply
     */
    Reply getValues(LoginInfo info, String key);

    /**
     * 新增字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    Reply addDict(LoginInfo info, Dict dict);

    /**
     * 编辑字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    Reply editDict(LoginInfo info, Dict dict);

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    Reply deleteDict(LoginInfo info, Long id);

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    Reply addDictKey(LoginInfo info, DictKey dictKey);

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    Reply editDictKey(LoginInfo info, DictKey dictKey);

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    Reply deleteDictKey(LoginInfo info, Long id);

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
    Reply getLog(Long id);
}
