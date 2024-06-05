package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.basedata.common.mapper.ParamMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 选项参数服务接口实现
 */
@Service
public class ParamServiceImpl implements ParamService {
    private final ParamMapper mapper;

    /**
     * 构造方法
     *
     * @param mapper ParamMapper
     */
    public ParamServiceImpl(ParamMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询选项参数
     *
     * @param dto 选项参数查询DTO
     * @return Reply
     */
    @Override
    public List<ParameterDto> getParameters(Parameter dto) {
        return mapper.getParameters(dto);
    }

    /**
     * 获取选项参数
     *
     * @param dto 选项参数查询DTO
     * @return Reply
     */
    @Override
    public ParameterDto getParameter(Parameter dto) {
        return mapper.getParameter(dto);
    }

    /**
     * 更新选项参数
     *
     * @param dto 选项参数
     */
    @Override
    public void setParameter(Parameter dto) {
        var data = mapper.getParameter(dto);
        if (data == null) {
            mapper.addParameter(dto);
        } else {
            dto.setId(data.getId());
            mapper.updateParameter(dto);
        }
    }
}
