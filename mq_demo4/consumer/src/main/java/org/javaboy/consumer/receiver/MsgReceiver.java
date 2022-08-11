package org.javaboy.consumer.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_NAME1)
    public void msgHandle1(String msg){
        log.info("handle1："+msg);
    }

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_NAME2)
    public void msgHandle2(String msg){
        log.info("handle2："+msg);
    }

}
