package com.ternence.spring.redis;

import com.ternence.spring.redis.cluster.lock.job.ParseJob;
import com.ternence.spring.redis.cluster.lock.job.SimpleJob;
import com.ternence.spring.redis.cluster.utils.RedissonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本项目的启动器
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/30 21:46
 */
public class ApplicationStarter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        context.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Destroy the application by graceful way ！");
            context.destroy(); //销毁容器
        }));
        LOGGER.info("The application has been started !");

        //test your code
        ApplicationStarter starter = new ApplicationStarter();
        starter.testJob();
    }

    private void testJob() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new ExecJobTask());
        service.submit(new ExecJobTask());
        service.submit(new ExecJobTask());
        try {
            //阻塞直到任务完成
            service.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final static class ExecJobTask extends Thread {
        private final static SimpleJob job = new ParseJob();
        private boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                try {
                    Thread.sleep(3000);
                    job.exec();
                } catch (InterruptedException e) {
                    LOGGER.info("This time task {} has been canceled.",
                            Thread.currentThread().getName());
                }
            }
        }
    }

    private void simpleTest() {

        LOGGER.info("get and delete ：{}", RedissonUtils.getStringBucket("test-key").getAndDelete());

        RedissonUtils.getStringBucket("test-key").set("this value is new !");

        LOGGER.info("get : {}", RedissonUtils.getStringBucket("test-key").get());
    }
}
