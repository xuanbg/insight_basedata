package com.insight.basedata.log;

import com.insight.basedata.common.Core;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.springframework.stereotype.Service;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务
 */
@Service
public class LogServiceImpl implements LogService {
    private final Core core;

    /**
     * 构造函数
     *
     * @param core LogMapper
     */
    public LogServiceImpl(Core core) {
        this.core = core;
    }

    /**
     * 获取日志列表
     *
     * @param info     用户关键信息
     * @param business 业务类型
     * @param keyword  查询关键词
     * @param page     分页页码
     * @param size     每页记录数
     * @return Reply
     */
    @Override
    public Reply getLogs(LoginInfo info, String business, String keyword, int page, int size) {
        return core.getLogs(info, business, keyword, page, size);
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
