package com.insight.basedata.common.client;

import com.insight.utils.Json;
import com.insight.utils.common.ApplicationContextHolder;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.message.Log;
import com.insight.basedata.common.entity.OperateType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author 宣炳刚
 * @date 2019-09-03
 * @remark RabbitMQ客户端
 */
public class LogClient {
    private static final RabbitTemplate TEMPLATE = ApplicationContextHolder.getContext().getBean(RabbitTemplate.class);

    /**
     * 记录操作日志
     *
     * @param info     用户关键信息
     * @param business 业务类型
     * @param type     操作类型
     * @param id       业务ID
     * @param content  日志内容
     */
    public static void writeLog(LoginInfo info, String business, OperateType type, Long id, Object content) {
        Log log = new Log();
        log.setAppId(info.getAppId());
        log.setTenantId(info.getTenantId());
        log.setType(type.toString());
        log.setBusiness(business);
        log.setBusinessId(id);
        log.setContent(Json.toBean(content, Object.class));
        log.setCreator(info.getName());
        log.setCreatorId(info.getId());

        TEMPLATE.convertAndSend("amq.topic", "insight.log", log);
    }
}
