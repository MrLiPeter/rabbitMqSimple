package com.example.message_delay_plugin.controller;

import com.example.message_delay_plugin.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        Message message = MessageBuilder.withBody(("hello!delay_queue" + new Date(System.currentTimeMillis())).getBytes(StandardCharsets.UTF_8))
                //Set the delay for messages to be sent to consumers to 3 seconds
                .setHeader("x-delay", 3000).build();
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE_NAME,RabbitConfig.DELAY_QUEUE_NAME,message);
    }
}
