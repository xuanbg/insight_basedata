package com.insight.basedata.common;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.basedata.common.mapper.LogMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
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
     * @param search 查询实体类
     * @return Reply
     */
    public Reply getLogs(Search search) {
        if (search.getId() == null) {
            throw new BusinessException("业务ID不能为空");
        }

        try (var page = PageHelper.startPage(search.getPageNum(), search.getPageSize()).setOrderBy(search.getOrderBy())
                .doSelectPage(() -> mapper.getLogs(search))) {
            var total = page.getTotal();
            return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
        }
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    public LogInfo getLog(Long id) {
        LogInfo log = mapper.getLog(id);
        if (log == null) {
            throw new BusinessException("ID不存在,未读取数据");
        }

        return log;
    }
}
