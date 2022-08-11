package org.javaboy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * fanout 交换机会将到达交换机的所有消息路由到与他绑定的所有队列上来
 */
public class FanoutConfig {
    public static final String FANOUT_QUEUE_NAME1="fanout_queue_name1";
    public static final String FANOUT_QUEUE_NAME2="fanout_queue_name2";
    public static final String FANOUT_EXCHANGE="fanout_exchange";

    @Bean
    Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE_NAME1,true,false,false);
    }

    @Bean
    Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE_NAME2,true,false,false);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE,true,false);
    }

    @Bean
    Binding fanoutBinding1(){
        return BindingBuilder
                .bind(fanoutQueue1())
                .to(fanoutExchange());
    }

    @Bean
    Binding fanoutBinding2(){
        return BindingBuilder
                .bind(fanoutQueue2())
                .to(fanoutExchange());
    }
}
