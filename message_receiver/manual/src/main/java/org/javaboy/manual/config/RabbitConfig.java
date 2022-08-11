package org.javaboy.manual.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String JAVABOY_QUEUE_NAME = "javaboy_queue_name";
    public static final String JAVABOY_EXCHANGE_NAME = "javaboy_exchange_name";

    @Bean
    Queue msgQueue(){
        return new
                Queue(JAVABOY_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange msgExchange(){
        return new DirectExchange(JAVABOY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding msgBinding(){
        return BindingBuilder.
                bind(msgQueue())
                .to(msgExchange())
                .with(JAVABOY_QUEUE_NAME);
    }

}
