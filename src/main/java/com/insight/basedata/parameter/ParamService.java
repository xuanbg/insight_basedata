package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParamSearchDto;
import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.utils.pojo.auth.LoginInfo;

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
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    List<ParameterDto> getParameters(LoginInfo info, ParamSearchDto dto);

    /**
     * 获取选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    ParameterDto getParameter(LoginInfo info, ParamSearchDto dto);

    /**
     * 更新选项参数
     *
     * @param info       用户关键信息
     * @param parameters 选项参数实体集合
     */
    void setParameter(LoginInfo info, List<Parameter> parameters);
}
