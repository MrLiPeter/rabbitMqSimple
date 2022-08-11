package org.javaboy.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    public static final String XIAOMI_QUEUE_NAME = "xiaomi_queue_name";
    public static final String HUAWEI_QUEUE_NAME = "huawei_queue_name";
    public static final String APPLE_QUEUE_NAME = "apple_queue_name";
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange_name";

    @Bean
    Queue xiaomiQueue(){
        return new Queue(XIAOMI_QUEUE_NAME,true,false,false);
    }

    @Bean
    Queue huaweiQueue(){
        return new Queue(HUAWEI_QUEUE_NAME,true,false,false);
    }

    @Bean
    Queue appleQueue(){
        return new Queue(APPLE_QUEUE_NAME,true,false,false);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding xiaomiBinding(){
        return BindingBuilder
                .bind(xiaomiQueue())
                .to(topicExchange())
                //这里#指的是通配符,意思是只要rout_key以xiaomi开头的消息,都会路由到xiaomiQueue
                .with("xiaomi.#");
    }

    @Bean
    Binding huaweiBinding(){
        return BindingBuilder
                .bind(huaweiQueue())
                .to(topicExchange())
                .with("huawei.#");
    }

    @Bean
    Binding appleBinding(){
        return BindingBuilder
                .bind(appleQueue())
                .to(topicExchange())
                //包含apple
                .with("#.apple.#");
    }

}
