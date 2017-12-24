package com.ternence.spring.rabbitmq.handler;

import com.ternence.spring.rabbitmq.consumer.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 消息处理器适配器
 *
 * @author Ternence
 * @version 1.0
 */
public class MessageAdapterHandler {
    private final static Logger logger = LoggerFactory.getLogger(MessageAdapterHandler.class);

    private ConcurrentMap<String, EventProcessorWrap> epwrapMap;

    public MessageAdapterHandler() {
        this.epwrapMap = new ConcurrentHashMap<>();
    }

    private class EventProcessorWrap {
        private EventProcessor eventProcessor;

        public EventProcessorWrap(EventProcessor eventProcessor) {
            this.eventProcessor = eventProcessor;
        }

        public <T> void process(T eventData) {
            eventProcessor.process(eventData);
        }
    }

}
