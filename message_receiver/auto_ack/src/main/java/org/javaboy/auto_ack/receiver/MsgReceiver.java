package org.javaboy.auto_ack.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.auto_ack.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues= RabbitConfig.JAVABOY_QUEUE_NAME)
    public void handle(String message){
         log.info("message:{}",message);
         int i = 1 / 0;

    }
}
