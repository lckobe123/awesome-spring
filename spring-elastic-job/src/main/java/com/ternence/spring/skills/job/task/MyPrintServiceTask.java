package com.ternence.spring.skills.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


/**
 * 定时执行打印任务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/16 10:58
 */
public class MyPrintServiceTask implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.printf("job context is : %s%n", shardingContext.toString());
    }
}
