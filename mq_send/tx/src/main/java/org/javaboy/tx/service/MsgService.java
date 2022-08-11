package org.javaboy.tx.service;

import org.javaboy.tx.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 1.客户端发送请求,将通信信道设置为事务模式 The client sends a request to set the communication channel to transaction mode
 * 2.服务端给出答复,同意将通信信道设置为事务模式 The server replies and agrees to set the communication channel to transaction mode
 * 3.发送消息 send a message
 * 4.提交事务 Commit the transaction
 * 5.服务端给出答复,确认事务提交 The server replies, confirming that the transaction is committed
 */
@Service
public class MsgService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_MSG_EXCHANGE_NAME,RabbitConfig.JAVABOY_MSG_QUEQUE_NAME,"Hello! Transactional!!");
        int i = 1/0;
    }



}
