package org.javaboy.queques_ttl.controller;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.queques_ttl.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_QUEUE_DELAY_EXCHANGE_NAME,RabbitConfig.JAVABOY_QUEUE_DELAY_QUEUE_NAME,"1111");
        log.info("给mq队列发送消息成功！");
    }
}
