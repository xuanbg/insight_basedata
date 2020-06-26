package com.insight.basedata.parameter;

import com.insight.basedata.common.dto.ParamSearchDto;
import com.insight.basedata.common.dto.ParameterDto;
import com.insight.basedata.common.entity.Parameter;
import com.insight.basedata.common.mapper.ParamMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.Util;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    @Override
    public Reply getParameters(LoginInfo info, ParamSearchDto dto) {
        dto.setTenantId(info.getTenantId());
        List<ParameterDto> list = mapper.getParameters(dto);

        return ReplyHelper.success(list);
    }

    /**
     * 获取选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数查询DTO
     * @return Reply
     */
    @Override
    public Reply getParameter(LoginInfo info, ParamSearchDto dto) {
        dto.setTenantId(info.getTenantId());
        ParameterDto data = mapper.getParameter(dto);
        if (data == null) {
            return ReplyHelper.fail("数据不存在");
        }

        return ReplyHelper.success(data);
    }

    /**
     * 更新选项参数
     *
     * @param info 用户关键信息
     * @param dto  选项参数实体
     * @return Reply
     */
    @Override
    public Reply setParameter(LoginInfo info, Parameter dto) {
        String id = dto.getId();
        if (id == null || id.isEmpty()) {
            id = Util.uuid();
            dto.setId(id);
            dto.setTenantId(info.getTenantId());
            dto.setCreator(info.getUserName());
            dto.setCreatorId(info.getUserId());
            dto.setCreatedTime(LocalDateTime.now());

            mapper.addParameter(dto);
        } else {
            mapper.updateParameter(dto);
        }

        return ReplyHelper.success();
    }
}
