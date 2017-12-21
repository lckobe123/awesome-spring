package com.ternence.spring.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现一个自动投资的bean
 *
 * @author Ternence
 * @version 1.0
 */
public class SimpleAutoInvestJob implements Job {
    private Logger logger = LoggerFactory.getLogger(SimpleAutoInvestJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("执行自动投资任务了");
    }
}
