package com.ternence.spring.rabbitmq.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/7/5 0:06
 */
@Component
public class ChangeMobileProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 30; i++) {
            rabbitTemplate.convertAndSend("modify_member_mobile", "第" + i + "条消息");
        }
    }
}
