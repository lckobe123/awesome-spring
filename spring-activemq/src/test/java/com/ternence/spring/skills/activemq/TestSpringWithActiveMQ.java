package com.ternence.spring.skills.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.*;

@ContextConfiguration("classpath:spring-activemq.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSpringWithActiveMQ {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private Destination queueDestination;
    @Resource
    private Destination topicDestination;

    @Test
    public void testProducer() {

        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage("Hello ActiveMQ !");
            }
        });

    }

    @Test
    public void testTopicProducer() {

        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage("Hello ActiveMQ !");
            }
        });

    }
}
