package org.javaboy.confirm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class RabbitConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback{

    public static String JAVABOY_QUEUE_NAME = "javaboy_queue_name";
    public static String JAVABOY_EXCHANGE_NAME = "javaboy_exchange_name";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(JAVABOY_EXCHANGE_NAME,true,false);
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setReturnsCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    @Bean
    Queue msgQueue(){
        return new Queue(JAVABOY_QUEUE_NAME,true,false,false);
    }

    @Bean
    Binding msgBinding(){
        return BindingBuilder
                .bind(msgQueue())
                .to(directExchange())
                .with(JAVABOY_QUEUE_NAME);
    }

    /**
     * 消息成功到达交换机,会触发该方法 This method is triggered when the message successfully reaches the exchange
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            //The message successfully reached the exchange
            log.info("{} 消息成功到达交换机",correlationData.getId());
        }else{
            log.info("{} 消息未成功到到交换机,原因:{}",correlationData.getId(),cause);
        }
    }

    /**
     * 消息未成功到达队列会,触发该方法 This method is triggered when the message fails to arrive on the queue
     * @param returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("{} 消息未成功到达队列",returned.getMessage().getMessageProperties().getCorrelationId());
    }

}
