package com.ternence.spring.quartz;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author Ternence
 * @version 1.0
 */
public class WebApplicationContextListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
    }
}
