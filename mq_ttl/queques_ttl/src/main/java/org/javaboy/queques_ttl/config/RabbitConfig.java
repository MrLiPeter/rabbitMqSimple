package org.javaboy.queques_ttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitConfig {
    public static final String JAVABOY_QUEUE_DELAY_QUEUE_NAME = "javaboy_queue_delay_queue_name";
    public static final String JAVABOY_QUEUE_DELAY_EXCHANGE_NAME = "javaboy_queue_delay_exchange_name";

    @Bean
    Queue messageDelayQueue(){
        HashMap<String, Object> args = new HashMap<>();
        //给队列设置过期时间,该队列的消息如果10s内没人消费则过期
        args.put("x-message-ttl",10000);
        return new Queue(JAVABOY_QUEUE_DELAY_QUEUE_NAME,true,false,false,args);
    }

    @Bean
    DirectExchange messageDelayExchange(){
        return new DirectExchange(JAVABOY_QUEUE_DELAY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding messageDelayQueueBinding(){
        return BindingBuilder
                .bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(JAVABOY_QUEUE_DELAY_QUEUE_NAME);
    }

}
