package com.insight.basedata.common.mapper;

import com.insight.basedata.common.dto.DictDto;
import com.insight.basedata.common.dto.DictKeyDto;
import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.basedata.common.entity.DictValue;
import com.insight.utils.pojo.base.JsonTypeHandler;
import com.insight.utils.pojo.base.Search;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典DAL
 */
@Mapper
public interface DictMapper {

    /**
     * 获取字典列表
     *
     * @param search 查询参数DTO
     * @return 字典DTO集合
     */
    @Select("<script>select * from icd_dict_key " +
            "<if test = 'longSet != null and longSet.size() > 0'>where app_id in " +
            "<foreach collection =\"longSet\" item=\"item\" index= \"index\" separator =\",\" open =\"(\" close =\") \">#{item}</foreach>" +
            "</if></script>")
    List<DictDto> getDicts(Search search);

    /**
     * 获取字典键值集合
     *
     * @param tenantId 租户ID
     * @param dictId   字典ID
     * @return 键值DTO集合
     */
    @Results({@Result(property = "extend", column = "extend", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select * from (select * from icd_dict_value where dict_id = #{dictId} and isnull(tenant_id) union all " +
            "select * from icd_dict_value where dict_id = #{dictId} and tenant_id = #{tenantId}) t order by t.index;")
    List<DictKeyDto> getDictKeys(Long tenantId, Long dictId);

    /**
     * 获取字典键值集合
     *
     * @param tenantId 租户ID
     * @param key      字典KEY
     * @return 键值DTO集合
     */
    @Results({@Result(property = "extend", column = "extend", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select v.* from icd_dict_key k join icd_dict_value v on v.dict_id = k.id " +
            "where k.`code` = #{key} and (isnull(v.tenant_id) or v.tenant_id = #{tenantId}) order by v.index;")
    List<DictValue> getValues(Long tenantId, String key);

    /**
     * 获取字典数据
     *
     * @param id 字典ID
     * @return 字典DTO
     */
    @Select("select * from icd_dict_key where id = #{id};")
    DictDto getDict(Long id);

    /**
     * 新增字典数据
     *
     * @param dict 字典实体类
     */
    @Insert("insert `icd_dict_key`(`id`, `app_id`, `app_name`, `code`, `name`, `remark`, `creator`, `creator_id`, `created_time`) " +
            "values (#{id}, #{appId}, #{appName}, #{code}, #{name}, #{remark}, #{creator}, #{creatorId}, #{createdTime});")
    void addDict(Dict dict);

    /**
     * 更新字典数据
     *
     * @param dict 字典实体类
     */
    @Update("update icd_dict_key set app_id = #{appId}, app_name = #{appName}, code = #{code}, name = #{name}, remark = #{remark} where id = #{id};")
    void updateDict(Dict dict);

    /**
     * 删除字典数据
     *
     * @param id 字典ID
     */
    @Delete("delete k, v from icd_dict_key k left join icd_dict_value v on v.dict_id = k.id where k.id = #{id};")
    void deleteDict(Long id);

    /**
     * 获取字典键值
     *
     * @param id 键值ID
     * @return 键值DTO
     */
    @Results({@Result(property = "extend", column = "extend", javaType = Object.class, typeHandler = JsonTypeHandler.class)})
    @Select("select * from icd_dict_value where id = #{id};")
    DictKey getDictKey(Long id);

    /**
     * 查询字典键值数量
     *
     * @param dictId 字典ID
     * @param code   键值编码
     * @param value  键值
     * @return 键值数量
     */
    @Select("select count(*) from icd_dict_value where dict_id = #{dictId} and (`code` = #{code} or `value` = #{value});")
    int getDictKeyCount(@Param("dictId") Long dictId, @Param("code") String code, @Param("value") String value);

    /**
     * 新增键值数据
     *
     * @param dictKey 键值实体
     */
    @Insert("insert icd_dict_value(id, dict_id, tenant_id, `index`, `code`, `value`, extend, remark, creator, creator_id, created_time) " +
            "values (#{id}, #{dictId}, #{tenantId}, #{index}, #{code}, #{value}, #{extend, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler}, " +
            "#{remark}, #{creator}, #{creatorId}, #{createdTime});")
    void addDictKey(DictKey dictKey);

    /**
     * 更新键值数据
     *
     * @param dictKey 键值实体
     */
    @Update("update icd_dict_value set `index` = #{index}, `code` = #{code}, `value` = #{value}, " +
            "extend = #{extend, typeHandler = com.insight.utils.pojo.base.JsonTypeHandler}, remark = #{remark} where id = #{id};")
    void updateDictKey(DictKey dictKey);

    /**
     * 删除键值数据
     *
     * @param id 键值ID
     */
    @Delete("delete from icd_dict_value where id = #{id};")
    void deleteDictKey(Long id);
}
