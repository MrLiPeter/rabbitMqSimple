package org.javaboy.confirm.controller;

import org.javaboy.confirm.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_EXCHANGE_NAME,
                RabbitConfig.JAVABOY_QUEUE_NAME,
                "hello!confirm",
                new CorrelationData(UUID.randomUUID().toString()));
    }

}
