package com.ternence.spring.quartz.test;

import com.ternence.spring.quartz.job.CronAutoInvestJob;
import com.ternence.spring.quartz.job.SimpleAutoInvestJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

/**
 * @author Ternence
 * @version 1.0
 */
public class QuartzDemoStarter {
    private Logger logger = LoggerFactory.getLogger(QuartzDemoStarter.class);

    public static void main(String[] args) throws InterruptedException, SchedulerException {
        //不检查更新
        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
        QuartzDemoStarter starter = new QuartzDemoStarter();
        Scheduler scheduler = starter.getScheduler();
        starter.cronStartJob(scheduler);

        //阻塞主线程
        Thread.sleep(Integer.MAX_VALUE);

        starter.stopJob(scheduler);
    }

    private void stopJob(Scheduler scheduler) throws SchedulerException {
        //关闭调度器
        scheduler.shutdown(true);
        logger.info("关闭定时任务");
    }

    /**
     * 使用cron trigger触发定时任务
     * <p>
     * cron 表达式解析:
     * <p>
     * 格式：秒 分 时 天 月 周 年
     * / 表示"每",比如在秒这个字段上写 /5 那就是每5s,所有字段都可以用这个符号
     * <p>
     * ? 表示不确定的值,只有星期可用
     *
     * @param scheduler Job 调度器
     * @throws SchedulerException 调度器异常
     */
    private void cronStartJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(CronAutoInvestJob.class)
                .withIdentity("cron-job", "hujin-cron").build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("* * * * * ?")
                .inTimeZone(TimeZone.getDefault());

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder)
                .withIdentity("cron-trigger", "hujin-cron")
                .startNow().build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }

    /**
     * 使用SimpleTrigger定义触发任务的时机
     *
     * @param scheduler Job调度器
     * @throws SchedulerException 调度器执行异常
     */
    private void simpleStartJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SimpleAutoInvestJob.class)
                .withIdentity("job", "hujin").build();

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule().withRepeatCount(100)
                .withIntervalInSeconds(1);

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "hujin")
                .withSchedule(scheduleBuilder)//定义Job执行次数
                .startNow()//立刻执行
                .build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);

        logger.info("开启定时任务");
        //开始调度任务
        scheduler.start();

        logger.info("任务执行次数:" + simpleTrigger.getRepeatCount());
    }

    private Scheduler getScheduler() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            return schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
