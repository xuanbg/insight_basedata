package com.insight.basedata.area;

import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.mapper.AreaMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
