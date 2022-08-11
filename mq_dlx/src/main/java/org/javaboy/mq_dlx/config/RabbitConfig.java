package org.javaboy.mq_dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String MSG_EXCHANGE_NAME ="msg_exchange_name";
    public static final String MSG_QUEUE_NAME="msg_queue_name";

    @Bean
    DirectExchange msgExchange(){
        return new DirectExchange(MSG_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue msgQueue(){
        Map<String,Object> args = new HashMap<>();
        //set message expiration time,when message expiration enter dead-letter queue at once
        args.put("x-message-ttl",0);
        //set dead-letter-exchange
        args.put("x-dead-letter-exchange",RabbitDlxConfig.DLX_EXCHANGE_NAME);
        //set routingKey for dead-letter queue
        args.put("x-dead-letter-routing-key",RabbitDlxConfig.DLX_QUEUE_NAME);
        return new Queue(MSG_QUEUE_NAME,true,false,false,args);
    }

    @Bean
    Binding msgBind(){
        return BindingBuilder.bind(msgQueue()).to(msgExchange()).with(MSG_QUEUE_NAME);
    }

}
