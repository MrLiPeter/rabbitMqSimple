package org.javaboy.message_delay_dlx.controller;

import org.javaboy.message_delay_dlx.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.JAVA_EXCHANGE_NAME,RabbitConfig.JAVA_QUEUE_NAME,"hello!delay_massage "+ new Date());
    }
}
