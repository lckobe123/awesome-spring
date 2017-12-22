package com.ternence.spring.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 使员工JobDetailFactoryBean管理的job需要继承QuartzJobBean定义任务的内容
 *
 * @author Ternence
 * @version 1.0
 */
public class SpringStyleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("现在时间为：" + new Date());
        //可以通过上下文获取到JobDataMap，这里面可以存放一些参数类型的数据
        JobDataMap dataMap = context.getMergedJobDataMap();
        String wish = (String) dataMap.get("wish");
        logger.info(wish);
    }
}
