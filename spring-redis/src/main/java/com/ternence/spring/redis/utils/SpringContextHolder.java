package com.ternence.spring.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/30 22:39
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringContextHolder.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
        LOGGER.info("Inject application context !");
    }

    /**
     * 千万注意不要再bean初始化期间调用这个方法,这样会导致试图用一个没有完全初始化的bean
     * 初始化另一个bean，这很危险
     */
    public static <T> T getBean(Class<T> type) {
        assertContextNotNull();
        if (type == null) return null;
        return applicationContext.getBean(type);
    }

    public static Object getBean(String beanName) {
        assertContextNotNull();
        if (beanName == null) return null;
        return applicationContext.getBean(beanName);
    }

    private static void assertContextNotNull() {
        if (applicationContext == null) {

            throw new IllegalStateException("The \"applicationContext\" has not been inject !");
        }
    }
}
