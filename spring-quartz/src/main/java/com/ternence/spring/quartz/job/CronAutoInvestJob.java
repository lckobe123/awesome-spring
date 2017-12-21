package com.ternence.spring.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现一个自动投资的bean
 *
 * @author Ternence
 * @version 1.0
 */
public class CronAutoInvestJob implements Job {
    private Logger logger = LoggerFactory.getLogger(CronAutoInvestJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Cron表达式执行自动投资任务了:{}", format.format(new Date()));
    }
}
