package org.javaboy;

import lombok.RequiredArgsConstructor;
import org.javaboy.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        //第一个参数队列名称，第二个参数消息内容
        for(int i=0;i<10;i++) {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, "hello 我的爱 "+i);
        }
    }

}
