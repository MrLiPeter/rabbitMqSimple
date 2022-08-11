package org.javaboy.server.controller;

import org.javaboy.server.config.RabbitConfig;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RpcServer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.RPC_MSG_QUEUE)
    public void process(Message message){
        byte[] body = message.getBody();
        //这个是服务端要返回的消息
        Message msg = MessageBuilder.withBody(("我是服务端我收到了客户端发来的消息:"+new String(body)).getBytes(StandardCharsets.UTF_8)).build();
        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
        rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE,RabbitConfig.RPC_REPLY_MSG_QUEUE,msg,correlationData);
    }
}
