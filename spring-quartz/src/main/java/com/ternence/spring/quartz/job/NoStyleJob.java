package com.ternence.spring.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ternence
 * @version 1.0
 */
public class NoStyleJob implements Serializable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void execute() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        logger.info("没有风格的Job,现在时间为：" + format.format(new Date()));
    }
}
