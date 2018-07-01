package com.ternence.spring.redis;

import com.ternence.spring.redis.cluster.lock.limit.CannotServiceException;
import com.ternence.spring.redis.cluster.lock.limit.OrderingServiceImpl;
import com.ternence.spring.redis.cluster.lock.limit.Service;
import com.ternence.spring.redis.cluster.utils.RedissonUtils;
import com.ternence.spring.redis.utils.SpringContextHolder;
import org.redisson.RedissonSemaphore;
import org.redisson.api.RSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流功能启动器
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/1 17:06
 */
public class CurrentLimitStarter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        context.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Destroy the application by graceful way ！");
            context.destroy(); //销毁容器
        }));
        LOGGER.info("The application has been started !");

        testSemaphore();

       /* ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            //提交一百个任务
            executor.submit(new OrderThread());
        }
        executor.shutdown();//直到前面的任务执行完毕之前不再接受新的任务*/
    }

    private static void testSemaphore() {

        RSemaphore rSemaphore = RedissonUtils.getSemaphore("my-semaphore");
        if (rSemaphore.isExists()) rSemaphore.delete();

        if (rSemaphore.trySetPermits(10)) {
            LOGGER.info("信号量设置成");
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {//如果许可不够,他会自动的增加许可数量
            executorService.submit((Runnable) () -> {
                while (true) {
                    try {
                        if (rSemaphore.tryAcquire()) {
                            LOGGER.info("{}获取到许可", Thread.currentThread().getName());
                        } else {
                            LOGGER.error("{}获取许可失败,{}", Thread.currentThread().getName(), new Exception("获取许可失败"));
                        }
                    } finally {
                        rSemaphore.release();
                        LOGGER.info("{}释放许可,剩余许可数:{}", Thread.currentThread().getName(), rSemaphore.availablePermits());
                    }
                }
            });
        }

    }

    private final static class OrderThread extends Thread {
        private boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                Service<String> service = SpringContextHolder.getBean(OrderingServiceImpl.class);
                try {
                    @SuppressWarnings("ConstantConditions")
                    String result = service.service();
                    LOGGER.info("下单结果:{}", result);
                } catch (CannotServiceException e) {
                    LOGGER.error("服务繁忙,请稍后重试。{}", e.getCause());
                }
            }
        }
    }
}
