package com.insight.basedata.common;

import com.insight.basedata.common.config.QueueConfig;
import com.insight.basedata.common.mapper.LogMapper;
import com.insight.utils.Json;
import com.insight.utils.pojo.Log;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2019-09-03
 * @remark
 */
@Component
public class Listener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LogMapper mapper;

    /**
     * 构造方法
     *
     * @param mapper LogMapper
     */
    public Listener(LogMapper mapper) {
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
    public void receiveLog(Channel channel, Message message) throws IOException {
        try {
            String body = new String(message.getBody());
            Log log = Json.toBean(body, Log.class);
            if (log == null) {
                return;
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