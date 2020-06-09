package com.insight.basedata.log;

import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;

/**
 * @author 宣炳刚
 * @date 2019-09-02
 * @remark 配置管理服务接口
 */
public interface LogService {

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
    Reply getLogs(LoginInfo info, String business, String keyword, int page, int size);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Reply getLog(String id);
}
