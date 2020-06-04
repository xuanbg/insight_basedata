package com.insight.basedata.area;

import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.entity.Area;
import com.insight.basedata.common.mapper.AreaMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划服务接口实现
 */
@Service
public class AreaServiceImpl implements AreaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AreaMapper mapper;

    /**
     * 构造方法
     *
     * @param mapper AreaMapper
     */
    public AreaServiceImpl(AreaMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取行政区划列表
     *
     * @return Reply
     */
    @Override
    public Reply getAreas() {
        List<AreaListDto> list = mapper.getAreas();

        return ReplyHelper.success(list);
    }

    /**
     * 查询全部省级行政区划
     *
     * @return Reply
     */
    @Override
    public Reply getProvinces() {
        List<AreaListDto> list = mapper.getProvinces();

        return ReplyHelper.success(list);
    }

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 行政区划ID
     * @return Reply
     */
    @Override
    public Reply getAreas(String id) {
        List<AreaListDto> list = mapper.getAreas(id);

        return ReplyHelper.success(list);
    }

    /**
     * 新增行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    @Override
    public Reply addArea(LoginInfo info, Area area) {
        area.setId(Util.uuid());
        area.setCreator(info.getUserName());
        area.setCreatorId(info.getUserId());
        area.setCreatedTime(LocalDateTime.now());
        mapper.addArea(area);

        return ReplyHelper.success();
    }

    /**
     * 编辑行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    @Override
    public Reply editArea(LoginInfo info, Area area) {
        AreaListDto data = mapper.getArea(area.getId());
        if (data == null){
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        mapper.updateArea(area);
        return ReplyHelper.success();
    }

    /**
     * 删除行政区划
     *
     * @param info 用户关键信息
     * @param id   行政区划ID
     * @return Reply
     */
    @Override
    public Reply deleteArea(LoginInfo info, String id) {
        AreaListDto data = mapper.getArea(id);
        if (data == null){
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        mapper.deleteArea(id);
        return ReplyHelper.success();
    }
}
