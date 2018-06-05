package com.ternence.spring.skills.activemq.consumer;

import com.ternence.spring.skills.activemq.producer.MessageProducerTest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import java.io.IOException;

import static com.ternence.spring.skills.activemq.ActivemqConnection.*;

/**
 * activemq消息消费者测试
 */
public class MessageConsumerTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageProducerTest.class);

    public static void main(String[] args) throws JMSException, IOException {
        LOGGER.info(" default_user:{}\r\n default_broker_bind_url:{}\r\n default_broker_bind_url:{}", DEFAULT_USER
                , DEFAULT_PASSWORD, DEFAULT_BROKER_BIND_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_USER, DEFAULT_PASSWORD, DEFAULT_BROKER_BIND_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        LOGGER.info("ActiveMQ server has been connected at now , connectionFactory:{}", connectionFactory);

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        LOGGER.info("MessageConsumer has been created at now , MessageConsumer:{}", consumer);

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage hello = (TextMessage) message;
                try {
                    LOGGER.info("The client received : {}", hello.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        LOGGER.info("stop " + String.valueOf(System.in.read()));

        LOGGER.info("Release resources ...");
        connection.close();
        session.close();
        consumer.close();
    }
}
