package com.ternence.spring.skills.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActiveMQMessageListener implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActiveMQMessageListener.class);

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {

            TextMessage textMessage = (TextMessage) message;

            try {
                LOGGER.info("received : {}", textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
