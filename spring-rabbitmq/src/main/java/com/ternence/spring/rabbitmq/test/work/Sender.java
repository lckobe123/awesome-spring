package com.ternence.spring.rabbitmq.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * work模型的mq消息发送者
 *
 * @author Ternence
 * @version 1.0
 */
public class Sender {
    private static final String QUEUE_NAME = "QUEUE_WORK";

    public static void main(String[] args) throws IOException, TimeoutException,
            InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //申明队列,如果没有就创建
        channel.queueDeclare(QUEUE_NAME, false, false,
                false, null);

        for (int i = 0; i < 5000; i++) {
            String content = "第" + i + "条消息";
            System.out.println(content);
            //exchange为空白字符串,表示默认的交换器(direct类型),队列名称作为routing key
            //direct类型的交换器是以队列名称作为routing key来查找队列的
            channel.basicPublish("", QUEUE_NAME, null, content.getBytes());
            //一秒发送一个消息
            Thread.sleep(1000);
        }

        channel.close();
        connection.close();
    }
}
