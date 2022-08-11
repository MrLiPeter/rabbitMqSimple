package org.javaboy.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(String msg){
      log.info("获取到msg:"+msg);
    }
}
