package com.ternence.spring.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ternence
 * @version 1.0
 */
public class NoStyleJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void execute() {
        logger.info("没有风格的Job");
    }
}
