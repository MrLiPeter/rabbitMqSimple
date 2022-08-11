package org.javaboy.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.client.config.RabbitConfig;
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
    public void hello(String message){
        //将来要发送的消息对象
        Message msg = MessageBuilder.withBody(message.getBytes(StandardCharsets.UTF_8)).build();
        //发送消息,方法的返回值是Server给出的响应
        Message result = rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_MSG_QUEUE, msg);
        if(result != null){
            //发送消息的CorrelationId
            String correlationId = msg.getMessageProperties().getCorrelationId();
            //返回消息的CorrelationId
            String spring_returned_message_correlation = (String) result.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
            if(correlationId.equals(spring_returned_message_correlation)){
                log.info("收到服务端响应:"+new String(result.getBody()));
            }
        }
    }

}
