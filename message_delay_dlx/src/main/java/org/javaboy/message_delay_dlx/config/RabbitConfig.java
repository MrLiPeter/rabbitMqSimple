package org.javaboy.message_delay_dlx.config;

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

    public static final String JAVA_QUEUE_NAME = "java_queue_name";
    public static final String JAVA_EXCHANGE_NAME = "java_exchange_name";
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_name";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";

    /**
     * message expiration time parameter identifier
     */
    public static final String X_MESSAGE_TTL = "x-message-ttl";

    /**
     *  dead-letter-exchange parameter identifier
     */
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    /**
     * dead-letter-routing-key parameter identifier
     */
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    DirectExchange msgExchange(){
        return new DirectExchange(JAVA_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue msgQueue(){
        Map args = new HashMap();
        //set queue messages to expire after 10 seconds
        args.put(X_MESSAGE_TTL,10000);
        // set dead-letter-exchange
        args.put(X_DEAD_LETTER_EXCHANGE,DELAY_EXCHANGE_NAME);
        //set dead-letter exchange
        args.put(X_DEAD_LETTER_ROUTING_KEY,DELAY_QUEUE_NAME);
        return new Queue(JAVA_QUEUE_NAME,true,false,false,args);
    }

    @Bean
    Binding msgBind(){
        return BindingBuilder
                .bind(msgQueue())
                .to(msgExchange())
                .with(JAVA_QUEUE_NAME);
    }


    @Bean
    DirectExchange dlxExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue dlxQueue(){
        return new Queue(DELAY_QUEUE_NAME,true,false,false);
    }

    @Bean
    Binding dlxBinding(){
        return BindingBuilder.
                bind(dlxQueue())
                .to(dlxExchange())
                .with(DELAY_QUEUE_NAME);
    }

}
