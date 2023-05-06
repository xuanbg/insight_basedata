package com.insight.basedata.area;

import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.entity.Area;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;

import java.util.List;

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
    List<AreaListDto> getAreas();

    /**
     * 查询全部省级行政区划
     *
     * @return Reply
     */
    List<AreaListDto> getProvinces();

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 行政区划ID
     * @return Reply
     */
    List<AreaListDto> getAreas(Long id);

    /**
     * 新增行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    Long addArea(LoginInfo info, Area area);

    /**
     * 编辑行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     */
    void editArea(LoginInfo info, Area area);

    /**
     * 删除行政区划
     *
     * @param info 用户关键信息
     * @param id   行政区划ID
     */
    void deleteArea(LoginInfo info, Long id);

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
    Log getLog(Long id);
}
