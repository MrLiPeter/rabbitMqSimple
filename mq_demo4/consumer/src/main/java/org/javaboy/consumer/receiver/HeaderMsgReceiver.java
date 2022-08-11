package org.javaboy.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.HeaderConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HeaderMsgReceiver {

    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_NAME_NAME)
    public void nameHeader(byte[] msg){
        log.info("nameHeader>>>:"+new String(msg,0, msg.length));
    }

    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_AGE_NAME)
    public void ageHeader(byte[] msg){
        log.info("ageHeader>>>:"+new String(msg,0, msg.length));
    }

}
