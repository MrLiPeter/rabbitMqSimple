package org.javaboy.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FanoutMsgReceiver {

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME1)
    public void handle1(String msg){
        log.info("handle1:"+msg);
    }

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME2)
    public void handle2(String msg){
        log.info("handle2:"+msg);
    }
}
