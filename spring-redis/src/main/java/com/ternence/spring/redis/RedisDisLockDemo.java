package com.ternence.spring.redis;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;

import java.util.concurrent.*;

/**
 * 使用redisson实现的分布式锁 测试效果
 *
 * @author Ternence
 * @version 1.0
 */
public class RedisDisLockDemo {
    private final static String SERVER_ADDRESS = "http://192.168.72.128:6379";
    private Count count = new Count(1);

    private Runnable task = () -> {
        Config config = new Config();
        config.useSingleServer().setAddress(SERVER_ADDRESS);
        RedissonClient client = Redisson.create(config);
        //他的锁是用一个hash值作为锁的
        RLock rLock = client.getLock("lock:bond");
        rLock.lock();//上锁，这是一个阻塞操作,会一直阻塞程序的执行
        try {
            count.countSub();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();//释放锁
        }
        client.shutdown();//关闭Redisson实例,释放资源
    };

    public static void main(String[] args) {
        RedisDisLockDemo redisDisLockDemo = new RedisDisLockDemo();
        redisDisLockDemo.testExaminationScene();
    }

    public RedisDisLockDemo() {
    }

    private void testExaminationScene() {
        //线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000, 1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100));

        CountDownLatch examStart = new CountDownLatch(1);// 考试开始的铃声

        CountDownLatch students = new CountDownLatch(10);//考生交卷结束的标志

        for (int i = 0; i < 10; i++) {
            Runnable task = () -> {
                System.out.println("考生" + Thread.currentThread().getName() + "进场等待中");
                try {
                    examStart.await();//开始阻塞，阻塞到开始铃声的响起来（也就是examStart倒计时为0的时候就开始了）
                    for (int j = 0; j < 5; j++) {
                        System.out.println("考生" + Thread.currentThread().getName() + "做完第" + (j + 1) + "题");

                        if (j < 4)
                            Thread.sleep((long) (Math.random() * 10 * 1000));//做完一道题随机等待
                    }
                    students.countDown();//考生交卷(减1)

                    System.out.println(Thread.currentThread().getName() + "交卷，还有" + students.getCount() + "人未交卷");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(task);//考生进场准备
        }

        examStart.countDown();//开始考试

        try {
            students.await();//阻塞，等待所有学生交卷
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有考生均交卷，考试完毕");
    }
}
