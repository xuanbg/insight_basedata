package com.insight.basedata.dict;

import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典服务接口
 */
public interface DictService {

    /**
     * 获取字典列表
     *
     * @param info    用户关键信息
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    Reply getDicts(LoginInfo info, String keyword, int page, int size);

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    Reply getDictKeys(LoginInfo info, String id);

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
    Reply deleteDict(LoginInfo info, String id);

    /**
     * 新增字典键值
     *
     * @param info 用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    Reply addDictKey(LoginInfo info, DictKey dictKey);

    /**
     * 编辑字典键值
     *
     * @param info 用户关键信息
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
    Reply deleteDictKey(LoginInfo info, String id);

    /**
     * 获取日志列表
     *
     * @param info    用户关键信息
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    Reply getLogs(LoginInfo info, String keyword, int page, int size);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Reply getLog(String id);
}
