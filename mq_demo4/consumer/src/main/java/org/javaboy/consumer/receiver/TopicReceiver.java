package org.javaboy.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicReceiver {

    @RabbitListener(queues = TopicConfig.XIAOMI_QUEUE_NAME)
    public void xiaomiHandle(String msg){
        log.info("xiaomiHandle:"+msg);
    }

    @RabbitListener(queues = TopicConfig.HUAWEI_QUEUE_NAME)
    public void huaweiHandle(String msg){
        log.info("huaweiHandle:"+msg);
    }

    @RabbitListener(queues = TopicConfig.APPLE_QUEUE_NAME)
    public void appleHandle(String msg){
        log.info("appleHandle:"+msg);
    }

}
