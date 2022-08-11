package com.example.message_delay_plugin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_name";

    @Bean
    Queue delayQueue(){
        return new Queue(DELAY_QUEUE_NAME,true,false,false);
    }

    @Bean
    CustomExchange customExchange(){
        Map args = new HashMap();
        //set the type of exchange
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME,"x-delayed-message",true,false,args);
    }

    @Bean
    Binding delayBinding(){
        return BindingBuilder.
                bind(delayQueue()).
                to(customExchange()).
                with(DELAY_QUEUE_NAME).noargs();
    }
}
