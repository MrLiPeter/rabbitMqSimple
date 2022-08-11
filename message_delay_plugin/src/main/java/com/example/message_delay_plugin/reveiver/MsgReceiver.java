package com.example.message_delay_plugin.reveiver;

import com.example.message_delay_plugin.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.DELAY_QUEUE_NAME)
    public void handleMsg(String message){
        log.info("handleMsg===={}",message);
    }
}
