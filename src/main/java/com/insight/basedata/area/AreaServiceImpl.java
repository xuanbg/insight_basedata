package com.insight.basedata.area;

import com.insight.basedata.common.Core;
import com.insight.basedata.common.client.LogClient;
import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.entity.Area;
import com.insight.basedata.common.mapper.AreaMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.OperateType;
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
    private static final String BUSINESS = "行政区划管理";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AreaMapper mapper;
    private final Core core;

    /**
     * 构造方法
     *
     * @param mapper AreaMapper
     * @param core   Core
     */
    public AreaServiceImpl(AreaMapper mapper, Core core) {
        this.mapper = mapper;
        this.core = core;
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
        String id = Util.uuid();
        area.setId(id);
        area.setCreator(info.getUserName());
        area.setCreatorId(info.getUserId());
        area.setCreatedTime(LocalDateTime.now());
        mapper.addArea(area);
        LogClient.writeLog(info, BUSINESS, OperateType.INSERT, id, area);

        return ReplyHelper.success(id);
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
        String id = area.getId();
        AreaListDto data = mapper.getArea(id);
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未更新数据");
        }

        mapper.updateArea(area);
        LogClient.writeLog(info, BUSINESS, OperateType.UPDATE, id, area);

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
        if (data == null) {
            return ReplyHelper.fail("ID不存在,未删除数据");
        }

        mapper.deleteArea(id);
        LogClient.writeLog(info, BUSINESS, OperateType.DELETE, id, data);

        return ReplyHelper.success();
    }

    /**
     * 获取日志列表
     *
     * @param info    用户关键信息
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    @Override
    public Reply getLogs(LoginInfo info, String keyword, int page, int size) {
        return core.getLogs(info, BUSINESS, keyword, page, size);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @Override
    public Reply getLog(String id) {
        return core.getLog(id);
    }
}
