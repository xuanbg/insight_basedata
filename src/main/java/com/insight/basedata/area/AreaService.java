package com.insight.basedata.area;

import com.insight.util.pojo.Reply;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划服务接口
 */
public interface AreaService {

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

}
