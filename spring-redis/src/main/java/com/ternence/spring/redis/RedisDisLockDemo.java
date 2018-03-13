package com.ternence.spring.redis;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;

import java.util.concurrent.*;

/**
 * 使用redisson实现的分布式锁 测试效果
 * <p>
 * redisson实现的锁并非是互斥的，一个锁可以被很多线程同时获取到,他们也可以同时操作一个资源，这应该是典型的乐观锁的实现
 *
 * @author Ternence
 * @version 1.0
 */
public class RedisDisLockDemo {
    private final static String SERVER_ADDRESS = "192.168.72.128:6379";
    private Count count = new Count(1);

    public static void main(String[] args) {
        RedisDisLockDemo redisDisLockDemo = new RedisDisLockDemo();
        redisDisLockDemo.testCommonLock();
    }


    private void testCommonLock() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000,
                1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100));

        //执行一个任务去获取锁,然后加锁,不释放，看看后面还能不能获取到锁
        executor.execute(() -> {
            Config config = new Config();
            config.useSingleServer().setAddress(SERVER_ADDRESS);
            RedissonClient client = Redisson.create(config);
            //他的锁是用一个hash值作为锁的,使用ttl观察这个锁的变化发现，生命时间到20的时候回自动变为30，循环变化，key就一直被锁住了
            //RLock rLock = client.getLock("bond");//这步骤只是创建了一个RLock对象,并非执行了获取锁的操作
            RLock rLock = client.getLock("bond");//这步骤只是创建了一个RLock对象,并非执行了获取锁的操作
            //isLocked()观察这把锁是否被任何的线程锁住,逻辑就是如果这个锁存在就是被人锁住了,否则就不是被锁住的
            //执行获取锁的操作,如果所已经被其他线程持有的话，这里就需要等待,否则获取成功锁，执行后面的逻辑
            //rLock.lock();//上锁，这个步骤会在redis中产生一个hash值，也就是锁,这是一个阻塞操作,会一直阻塞到锁的释放
            rLock.lock(3, TimeUnit.SECONDS);//上一个带有超时特性的锁,
            System.out.println("获取到锁：" + rLock.isLocked());
        });

        //这里将会产生许多等待的线程,当锁放开之后大约10s左右会有下一个线程获得锁
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                Config config = new Config();
                config.useSingleServer().setAddress(SERVER_ADDRESS);
                RedissonClient client = Redisson.create(config);
                //他的锁是用一个hash值作为锁的,获取一个公平锁
                RLock rLock = client.getLock("bond");
                //rLock.lock();//执行获取锁的操作,如果所已经被其他线程持有的话，这里就需要等待,否则获取成功锁，执行后面的逻辑
                rLock.lock(3, TimeUnit.SECONDS);//上一个带有超时特性的锁
                System.out.println(Thread.currentThread().getName() + " 获取到锁");
            });
        }

        executor.shutdown();//不接受任何新的任务,但是他会等前面的任务有序的执行完毕
    }


    private void testDisLock() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000, 1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100));

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable task = () -> {
                Config config = new Config();
                config.useSingleServer().setAddress(SERVER_ADDRESS);
                RedissonClient client = Redisson.create(config);
                //他的锁是用一个hash值作为锁的,获取一个公平锁
                RLock rLock = client.getLock("lock:");
                rLock.lock();//上锁，这是一个阻塞操作,会一直阻塞程序的执行
                if (rLock.isLocked()) {
                    try {
                        count.countSub();
                        System.out.println(Thread.currentThread().getName() + "执行任务i=" + finalI + " count=" + count.getCount());
                        Thread.sleep(5 * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getName() + "任务完成，释放锁");
                        rLock.unlock();//释放锁
                        client.shutdown();//关闭Redisson实例,释放资源
                    }
                } else {
                    System.out.println("当前线程" + Thread.currentThread().getName() + "并没有锁定这个资源");
                }

            };
            executor.execute(task);
        }

        executor.shutdown();//终止线程池

    }


    @SuppressWarnings("unused")
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
