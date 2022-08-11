package org.javaboy.message_ttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String javaboy_message_delay_queue_name = "javaboy_message_delay_queue_name";
    public static final String javaboy_message_delay_exchange_name = "javaboy_message_delay_exchange_name";

    @Bean
    Queue messageDelayQueue(){
        return new Queue(javaboy_message_delay_queue_name,true,false,false);
    }

    @Bean
    DirectExchange messageDelayExchange(){
        return new DirectExchange(javaboy_message_delay_exchange_name,true,false);
    }

    @Bean
    Binding messageDelayQueueBinding(){
        return BindingBuilder
                .bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(javaboy_message_delay_queue_name);
    }

}
