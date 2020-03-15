package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.AreaListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划DAL
 */
@Mapper
public interface AreaMapper {

    /**
     * 查询全部省级行政区划
     *
     * @return 行政区划集合
     */
    @Select("select id, parent_id, `code`, `name`, alias from icd_area where parent_id is null order by `code`;")
    List<AreaListDto> getProvinces();

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 行政区划ID
     * @return 行政区划集合
     */
    @Select("select id, parent_id, `code`, `name`, alias from icd_area where parent_id = #{id} order by `code`;")
    List<AreaListDto> getAreas(String id);
}
