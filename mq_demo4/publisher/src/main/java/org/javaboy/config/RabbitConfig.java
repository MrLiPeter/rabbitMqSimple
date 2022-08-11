package org.javaboy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lzp
 * @date 2022/8/8
 * direct :这种路由策略,将消息绑定在exchange上，当消息到达交换机的时候，消息会携带一个routing_key,然后交换机找到一个名为routing_key的队列将消息路由过去
 */
@Configuration
public class RabbitConfig {

    public static final String DIRECT_QUEUE_NAME1="direct_queue_name1";
    public static final String DIRECT_QUEUE_NAME2="direct_queue_name2";
    public static final String DIRECT_EXCHANGE_NAME="direct_exchange_name";

    @Bean
    Queue directQueue1(){
        return new Queue(DIRECT_QUEUE_NAME1,true,false,false);
    }

    @Bean
    Queue directQueue2(){
        return new Queue(DIRECT_QUEUE_NAME2,true,false,false);
    }

    @Bean
    DirectExchange directExchange(){
        //交换机名称
        //交换机是否持久化
        //没有与之绑定的交换机队列是否自动删除
        return new DirectExchange(DIRECT_EXCHANGE_NAME,true,false);
    }

    /**
     *这个定义是将交换机和队列绑定起来
     * @return
     */
    @Bean
    Binding directBind1(){
        return BindingBuilder.
                //设置绑定的队列
                bind(directQueue1())
                //设置绑定的交换机
                .to(directExchange())
                //设置routingkey
                .with(DIRECT_QUEUE_NAME1);
    }

    @Bean
    Binding directBind2(){
        return BindingBuilder.
                        bind(directQueue2())
                .to(directExchange())
                .with(DIRECT_QUEUE_NAME2);
    }

}
