package org.javaboy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 交换机根据消息的头信息来决定消息去哪一个队列
 */
@Configuration
public class HeaderConfig {
    public static final String HEADER_QUEUE_NAME_NAME = "header_queue_name_name";
    public static final String HEADER_QUEUE_AGE_NAME = "header_queue_age_name";
    public static final String HEADER_EXCHANGE_NAME = "header_exchange_name";

    @Bean
    Queue headerNameQueue(){
        return new Queue(HEADER_QUEUE_NAME_NAME,true,false,false);
    }

    @Bean
    Queue headerAgeQueue(){
        return new Queue(HEADER_QUEUE_AGE_NAME,true,false,false);
    }

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange(HEADER_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding nameBanding(){
        return BindingBuilder
                .bind(headerNameQueue())
                .to(headersExchange())
                //如果将来消息头部有name属性就算匹配成功
                .where("name").exists();
    }

    @Bean
    Binding ageBanding(){
        return BindingBuilder
                .bind(headerAgeQueue())
                .to(headersExchange())
                //如果将来消息头部有age属性并且age的值为99算匹配成功
                .where("age").matches(99);
    }
}
