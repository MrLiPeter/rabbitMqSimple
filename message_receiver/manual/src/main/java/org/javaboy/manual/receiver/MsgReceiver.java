package org.javaboy.manual.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.javaboy.manual.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


//@Configuration
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues= RabbitConfig.JAVABOY_QUEUE_NAME)
    public void handle(Message message, Channel channel){
         //获取消息的一个标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            //start consuming messages
            byte[] body = message.getBody();
            String s = new String(body);
            log.info("message content:{}",s);
            int i=1/0;
            //消费完成后手动ack Manually ACK after consumption
            //第一个参数是消息的标记,第二个参数如果为false,表示仅仅确认当前消息,第三个参数如果为true表示之前所有的消息都确认消费成功
            /*
             * The first argument is the Tag of the message.
             * The second argument, if false, confirms only the current message.
             * The third argument, If true, it confirms that all previous messages have been consumed successfully
             */
            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            //手动nack,告诉mq这条消息消费失败
            try {
                /*
                 * The first argument is the Tag of the message.
                 * The second argument, if false, confirms only the current message.
                 * The third argument ,if true the message is re-queued,else enter dead-letter-queue
                 */
                channel.basicNack(deliveryTag,false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
