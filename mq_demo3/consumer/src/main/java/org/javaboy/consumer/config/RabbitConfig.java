package org.javaboy.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lzp
 * @date 2022/8/8
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME="hello_world_queue_name";

    @Bean
    Queue helloWorldQueue(){
        return new Queue(QUEUE_NAME,true,false,false);
    }
}
