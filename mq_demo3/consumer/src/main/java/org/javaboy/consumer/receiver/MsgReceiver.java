package org.javaboy.consumer.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.javaboy.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(Message message, Channel channel) throws IOException {
      log.info("获取到msg1:"+message.getPayload());
      //确认消息,就是告诉RabbitMQ这条消息我已经消费成功了
      channel.basicAck((Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),false);
    }

    /**
     * concurrency指的是并发数量，即开启20个线程去消费该消息
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME,concurrency = "20")
    public void handleMsg2(Message message, Channel channel) throws IOException {
        //拒绝消费该消息,requeue表示拒绝的消息是否重新进入队列中
        channel.basicReject((Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),true);
    }
}
