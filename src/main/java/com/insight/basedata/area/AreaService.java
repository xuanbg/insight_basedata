package com.insight.basedata.area;

import com.insight.basedata.common.entity.Area;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划服务接口
 */
public interface AreaService {

    /**
     * 获取行政区划列表
     *
     * @return Reply
     */
    Reply getAreas();

    /**
     * 查询全部省级行政区划
     *
     * @return Reply
     */
    Reply getProvinces();

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 行政区划ID
     * @return Reply
     */
    Reply getAreas(String id);

    /**
     * 新增行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    Reply addArea(LoginInfo info, Area area);

    /**
     * 编辑行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    Reply editArea(LoginInfo info, Area area);

    /**
     * 删除行政区划
     *
     * @param info 用户关键信息
     * @param id   行政区划ID
     * @return Reply
     */
    Reply deleteArea(LoginInfo info, String id);

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
