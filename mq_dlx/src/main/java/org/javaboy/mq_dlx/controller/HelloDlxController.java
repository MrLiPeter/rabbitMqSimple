package org.javaboy.mq_dlx.controller;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.mq_dlx.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloDlxController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.MSG_EXCHANGE_NAME,RabbitConfig.MSG_QUEUE_NAME,"test msg");
        log.info("send a message to MSG_QUEUE_NAME,expiration time is 0 second");
    }
}
