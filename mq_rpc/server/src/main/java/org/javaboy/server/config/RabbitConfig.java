package org.javaboy.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String RPC_MSG_QUEUE = "rpc_msg_queue";
    public static final String RPC_REPLY_MSG_QUEUE = "rpc_reply_msg_queue";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    @Bean
    Queue msgQueue(){
        return new Queue(RPC_MSG_QUEUE,true,false,false);
    }

    @Bean
    Queue replyQueue(){
        return new Queue(RPC_REPLY_MSG_QUEUE,true,false,false);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(RPC_EXCHANGE);
    }

    @Bean
    Binding msgBind(){
        return BindingBuilder
                .bind(msgQueue())
                .to(topicExchange())
                .with(RPC_MSG_QUEUE);
    }

    @Bean
    Binding replyBind(){
        return BindingBuilder
                .bind(replyQueue())
                .to(topicExchange())
                .with(RPC_REPLY_MSG_QUEUE);
    }

}
