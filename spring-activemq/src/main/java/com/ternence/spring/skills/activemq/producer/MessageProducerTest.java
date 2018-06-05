package com.ternence.spring.skills.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static com.ternence.spring.skills.activemq.ActivemqConnection.*;

/**
 * activemq的消息生产者
 */
public class MessageProducerTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageProducerTest.class);

    /**
     * Hello world
     *
     * @param args 命令行启动参数
     */
    public static void main(String[] args) throws JMSException {
        LOGGER.info(" default_user:{}\r\n default_broker_bind_url:{}\r\n default_broker_bind_url:{}", DEFAULT_USER
                , DEFAULT_PASSWORD, DEFAULT_BROKER_BIND_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_USER, DEFAULT_PASSWORD, DEFAULT_BROKER_BIND_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        LOGGER.info("ActiveMQ server has been connected at now , connectionFactory:{}", connectionFactory);

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);//如果队列存在就会直接返回队列，而不是新创建
        MessageProducer producer = session.createProducer(queue);

        LOGGER.info("MessageProducer has been created at now , MessageProducer:{}", producer);

        TextMessage hello = new ActiveMQTextMessage();
        hello.setText("This message is hello message!");
        producer.send(hello);

        LOGGER.info("Message has been send at now , MessageProducer:{}", producer);

        LOGGER.info("Release resources ...");
        connection.close();
        session.close();
        producer.close();
    }
}
