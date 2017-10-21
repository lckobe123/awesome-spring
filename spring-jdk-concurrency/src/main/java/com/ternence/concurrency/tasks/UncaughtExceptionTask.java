package com.ternence.concurrency.tasks;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 定义一个任务，抛出未捕获的运行时异常
 */
public class UncaughtExceptionTask implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("eh=" + t.getUncaughtExceptionHandler());
        throw new RuntimeException("我是一个未捕获的异常");
    }
}
