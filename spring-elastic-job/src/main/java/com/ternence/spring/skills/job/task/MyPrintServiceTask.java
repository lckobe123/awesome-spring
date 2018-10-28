package com.ternence.spring.skills.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;


/**
 * 定时执行打印任务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/16 10:58
 */
@Slf4j
public class MyPrintServiceTask implements SimpleJob {

    /**
     * 可以实现执行不同分片的时候做不同的事情,这样就可以分布式执行任务了,不同的分片执行不同的逻辑
     * <p>
     * 执行对应分片逻辑的机器自然就执行了任务的某一部分
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("job context is : {}", shardingContext.toString());
        switch (shardingContext.getShardingItem()) {
            case 0:
                log.error("执行分片0:A");
                break;
            case 1:
                log.error("执行分片1:B");
                break;
            case 2:
                log.error("执行分片2:C");
                break;
            default:
                log.error("没有分片项满足");
                break;
        }
    }
}
