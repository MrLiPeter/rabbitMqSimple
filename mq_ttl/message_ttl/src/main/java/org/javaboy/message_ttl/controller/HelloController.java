package org.javaboy.message_ttl.controller;

import org.javaboy.message_ttl.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){
        //创建一条10s后过期的消息,如果消息到达队列10s后没人消费,就自动过期
        Message message = MessageBuilder.
                withBody("hello javaBoy".getBytes(StandardCharsets.UTF_8))
                .setExpiration("10000").build();
        rabbitTemplate.send(RabbitConfig.javaboy_message_delay_exchange_name,
                RabbitConfig.javaboy_message_delay_queue_name,message);
    }
}
