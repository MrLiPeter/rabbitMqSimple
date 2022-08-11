package org.javaboy.confirm;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.confirm.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ConfirmApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        //poll mode
        Object o = rabbitTemplate.receiveAndConvert(RabbitConfig.JAVABOY_QUEUE_NAME);
        log.info("s={}",o);
    }

}
