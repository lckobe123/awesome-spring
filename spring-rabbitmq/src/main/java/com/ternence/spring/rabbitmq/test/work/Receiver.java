package com.ternence.spring.rabbitmq.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;

import java.io.IOException;

/**
 * work模型的MQ消息的接收者
 *
 * @author Ternence
 * @version 1.0
 */
public class Receiver {
    private static final String QUEUE_NAME = "QUEUE_WORK";

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //不要同事给一个服务器推送N条消息
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        //basic consume命令会将信道置为接收模式,处理完一条消息之后就会自动获取吓一条消息
        //QueueingConsumer会将收到的消息都放在一个阻塞队列当中,避免消息丢失
        channel.basicConsume(QUEUE_NAME, consumer);
        //循环处理消息

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            String message = new String(delivery.getBody());

            System.out.println("收到消息:" + message);

            Thread.sleep(200);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}
