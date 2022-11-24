package com.insight.basedata.log;

import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;

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
     * @param search   查询参数DTO
     * @return Reply
     */
    Reply getLogs(LoginInfo info, String business, Search search);

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    Reply getLog(Long id);
}
