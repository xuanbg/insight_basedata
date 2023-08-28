package com.insight.basedata.common;

import com.insight.basedata.common.config.QueueConfig;
import com.insight.basedata.common.mapper.CoreMapper;
import com.insight.utils.pojo.message.Log;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019-09-03
 * @remark
 */
@Component
public class Listener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CoreMapper mapper;

    /**
     * 构造方法
     *
     * @param mapper LogMapper
     */
    public Listener(CoreMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 从队列订阅日志消息
     *
     * @param channel Channel
     * @param message Message
     * @throws IOException IOException
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConfig.PROCESS_QUEUE_NAME)
    public void receiveLog(@Payload Log log, Channel channel, Message message) throws IOException {
        try {
            if (log == null) {
                return;
            }

            if (log.getContent() instanceof String) {
                Map<String, Object> map = new HashMap<>();
                map.put("Log", log.getContent());
                log.setContent(map);
            }

            log.setCreatedTime(LocalDateTime.now());
            mapper.addLog(log);
        } catch (Exception ex) {
            logger.error("发生异常: {}", ex.getMessage());
            channel.basicPublish(QueueConfig.DELAY_EXCHANGE_NAME, QueueConfig.DELAY_QUEUE_NAME, null, message.getBody());
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}