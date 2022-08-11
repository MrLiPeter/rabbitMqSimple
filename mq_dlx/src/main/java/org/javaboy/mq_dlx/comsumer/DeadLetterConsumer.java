package org.javaboy.mq_dlx.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.mq_dlx.config.RabbitDlxConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeadLetterConsumer{

     @RabbitListener(queues = RabbitDlxConfig.DLX_QUEUE_NAME)
     public void handle(String message){
         log.info("DeadLetterConsumer:"+message);
     }
}
