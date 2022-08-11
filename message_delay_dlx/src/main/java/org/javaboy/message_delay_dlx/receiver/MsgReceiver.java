package org.javaboy.message_delay_dlx.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.message_delay_dlx.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.DELAY_QUEUE_NAME)
    public void handle(String msg){
        log.info("handle:{}",msg);
    }
}
