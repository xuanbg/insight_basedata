package com.insight.basedata.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insight.basedata.common.mapper.LogMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.Log;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/5
 * @remark
 */
@Component
public class Core {
    private final LogMapper mapper;

    /**
     * 构造方法
     * @param mapper LogMapper
     */
    public Core(LogMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取日志列表
     *
     * @param info 租户ID
     * @param business 业务类型
     * @param keyword  查询关键词
     * @param page     分页页码
     * @param size     每页记录数
     * @return Reply
     */
    public Reply getLogs(LoginInfo info, String business, String keyword, int page, int size) {
        PageHelper.startPage(page, size);
        List<Log> logs = mapper.getLogs(info.getAppId(), info.getTenantId(), business, keyword);
        PageInfo<Log> pageInfo = new PageInfo<>(logs);

        return ReplyHelper.success(logs, pageInfo.getTotal());
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    public Reply getLog(String id) {
        Log log = mapper.getLog(id);
        if (log == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        return ReplyHelper.success(log);
    }
}
