package org.javaboy.tx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {

    public static final String JAVABOY_MSG_QUEQUE_NAME = "javaboy_msg_queque_name";
    public static final String JAVABOY_MSG_EXCHANGE_NAME = "javaboy_msg_exchange_name";

    @Bean
    Queue msgQueue(){
        return new Queue(JAVABOY_MSG_QUEQUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(JAVABOY_MSG_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding msgBinding(){
        return BindingBuilder.
                bind(msgQueue())
                .to(directExchange())
                .with(JAVABOY_MSG_QUEQUE_NAME);
    }

    @Bean
    RabbitTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new RabbitTransactionManager(connectionFactory);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //enable transaction mode
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

}
