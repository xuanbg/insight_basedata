package com.insight.basedata.common;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.mapper.LogMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import com.insight.utils.pojo.message.Log;
import org.springframework.stereotype.Component;

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
     *
     * @param mapper LogMapper
     */
    public Core(LogMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取日志列表
     *
     * @param info     租户ID
     * @param business 业务类型
     * @param search   查询实体类
     * @return Reply
     */
    public Reply getLogs(LoginInfo info, String business, Search search) {
        search.setValue(business);
        search.setAppId(info.getAppId());
        search.setTenantId(info.getTenantId());
        var page = PageHelper.startPage(search.getPageNum(), search.getPageSize())
                .setOrderBy(search.getOrderBy()).doSelectPage(() -> mapper.getLogs(search));

        var total = page.getTotal();
        return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    public Reply getLog(Long id) {
        Log log = mapper.getLog(id);
        if (log == null) {
            return ReplyHelper.fail("ID不存在,未读取数据");
        }

        return ReplyHelper.success(log);
    }
}
