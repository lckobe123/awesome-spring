package com.ternence.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * 在Spring容器启动的时候接受一下事件
 *
 * @author 陶江航
 * @version 1.0
 * @since 2017/9/3
 */
public class WebApplicationStartListener extends ContextLoaderListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        logger.info("欢迎使用");
        logger.info(" ____  ____  ____  _  _      _____     _____  ____  ____  _      ____  ____  ____  _____  _  ____  _     \n" +
                "/ ___\\/  __\\/  __\\/ \\/ \\  /|/  __/    /__ __\\/  __\\/  _ \\/ \\  /|/ ___\\/  _ \\/   _\\/__ __\\/ \\/  _ \\/ \\  /|\n" +
                "|    \\|  \\/||  \\/|| || |\\ ||| |  ______ / \\  |  \\/|| / \\|| |\\ |||    \\| / \\||  /    / \\  | || / \\|| |\\ ||\n" +
                "\\___ ||  __/|    /| || | \\||| |_//\\____\\| |  |    /| |-||| | \\||\\___ || |-|||  \\__  | |  | || \\_/|| | \\||\n" +
                "\\____/\\_/   \\_/\\_\\\\_/\\_/  \\|\\____\\      \\_/  \\_/\\_\\\\_/ \\|\\_/  \\|\\____/\\_/ \\|\\____/  \\_/  \\_/\\____/\\_/  \\|\n" +
                "                                                                                                         ");
        logger.info("Power by Ternence 陶江航，Copyright©2009-2018");
    }

}
