package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.entity.Area;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划DAL
 */
@Mapper
public interface AreaMapper {

    /**
     * 获取行政区划列表
     *
     * @return 行政区划列表
     */
    List<AreaListDto> getAreas();

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
    List<AreaListDto> getAreas(Long id);

    /**
     * 查询指定行政区划
     *
     * @param id 行政区划ID
     * @return 行政区划DTO
     */
    @Select("select id, parent_id, `code`, `name`, alias from icd_area where id = #{id};")
    AreaListDto getArea(Long id);

    /**
     * 新增行政区划数据
     *
     * @param area 行政区划实体
     */
    @Insert("insert icd_area(id, parent_id, `code`, `name`, alias, creator, creator_id, created_time) " +
            "values (#{id}, #{parentId}, #{code}, #{name}, #{alias}, #{creator}, #{creatorId}, #{createdTime});")
    void addArea(Area area);

    /**
     * 更新行政区划数据
     *
     * @param area 行政区划实体
     */
    @Update("update icd_area set parent_id = #{parentId}, `code` = #{code}, `name` = #{name}, alias = #{alias} where id = #{id};")
    void updateArea(Area area);

    /**
     * 删除行政区划数据
     *
     * @param id 行政区划ID
     */
    @Delete("delete p, c, d from icd_area p left join icd_area c on c.parent_id = p.id left join icd_area d on d.parent_id = c.id where p.id = #{id};")
    void deleteArea(Long id);
}
