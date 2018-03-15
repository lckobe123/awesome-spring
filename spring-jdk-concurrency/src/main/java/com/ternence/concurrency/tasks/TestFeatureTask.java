package com.ternence.concurrency.tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试BlockingQueue
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class TestFeatureTask {

    //可以生成计算结果的Runnable
    private FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
        System.out.println("blocking ...");
        Thread.sleep(3 * 1000);
        System.out.println("has blocked");
        return "hello world the future task";
    });
    private Thread myStringTaskThread = new Thread(stringFutureTask);

    public static void main(String[] args) {

        new TestFeatureTask().test();
    }

    public void test() {
        myStringTaskThread.start();
        try {
            String result = stringFutureTask.get();

            System.out.println("获取到结果:" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
