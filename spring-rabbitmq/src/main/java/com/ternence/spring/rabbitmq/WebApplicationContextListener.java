package com.ternence.spring.rabbitmq;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author Ternence
 * @version 1.0
 */
public class WebApplicationContextListener extends ContextLoaderListener{

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }
}
