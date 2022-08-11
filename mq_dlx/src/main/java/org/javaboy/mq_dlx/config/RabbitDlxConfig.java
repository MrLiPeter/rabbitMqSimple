package org.javaboy.mq_dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDlxConfig {
    public static final String DLX_EXCHANGE_NAME ="dlx_exchange_name";
    public static final String DLX_QUEUE_NAME="dlx_queue_name";

    @Bean
    DirectExchange dlxExchange(){
        return new DirectExchange(DLX_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue dlxQueue(){
        return new Queue(DLX_QUEUE_NAME,true,false,false);
    }

    @Bean
    Binding dlxBind(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(DLX_QUEUE_NAME);
    }

}
