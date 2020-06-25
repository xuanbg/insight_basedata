package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParamSearchDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数服务接口
 */
public interface ParamService {

    /**
     * 获取选项参数列表
     *
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    Reply getParameters(LoginInfo info, ParamSearchDto dto);

    /**
     * 获取选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    Reply getParameter(LoginInfo info, ParamSearchDto dto);

    /**
     * 更新选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数实体
     * @return Reply
     */
    Reply setParameter(LoginInfo info, Parameter dto);
}
