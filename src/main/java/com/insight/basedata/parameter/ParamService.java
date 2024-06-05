package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数服务接口
 */
public interface ParamService {

    /**
     * 查询选项参数
     *
     * @param dto 选项参数查询DTO
     * @return Reply
     */
    List<ParameterDto> getParameters(Parameter dto);

    /**
     * 获取选项参数
     *
     * @param dto 选项参数查询DTO
     * @return Reply
     */
    ParameterDto getParameter(Parameter dto);

    /**
     * 更新选项参数
     *
     * @param dto 选项参数
     */
    void setParameter(Parameter dto);
}
