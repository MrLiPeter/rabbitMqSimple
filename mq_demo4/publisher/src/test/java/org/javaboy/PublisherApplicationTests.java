package org.javaboy;

import org.javaboy.config.FanoutConfig;
import org.javaboy.config.HeaderConfig;
import org.javaboy.config.RabbitConfig;
import org.javaboy.config.TopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME,RabbitConfig.DIRECT_QUEUE_NAME1,"发给队列1");
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME,RabbitConfig.DIRECT_QUEUE_NAME2,"发给队列2");
    }

    @Test
    void test1(){
        rabbitTemplate.convertAndSend
                (FanoutConfig.FANOUT_EXCHANGE,
                        null,"hello 扇形交换机");
    }

    @Test
    void testTopicExchange(){
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,"xiaomi.news","小米新闻");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,"huawei.wallPaper","华为壁纸");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,"huawei.apple" ,"华为手机发苹果");
    }

    @Test
    void testHeadersExchange(){
        Message nameMessage = MessageBuilder.withBody("hello 张三!!".getBytes()).setHeader("name","zhangSan").build();
        rabbitTemplate.send(HeaderConfig.HEADER_EXCHANGE_NAME,null,nameMessage);
        Message ageMessage = MessageBuilder.withBody("Hi 99岁的伙计".getBytes()).setHeader("age",99).build();
        rabbitTemplate.send(HeaderConfig.HEADER_EXCHANGE_NAME,null,ageMessage);
    }
}
