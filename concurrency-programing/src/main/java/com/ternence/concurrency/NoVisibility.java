package com.ternence.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试数据的可见性
 */
public class NoVisibility {
    private static boolean ready = false;
    private static int number = 0;

    private static class ReaderTask implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println("yield");
            }
            System.out.println("number:" + number);
        }
    }

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println("yield");
            }
            System.out.println("number:" + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 使用线程池ExecutorService就不会发生指令重排，第一次都是先打印yield，
        // 后面才会看到主线程对ready和number的修改
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ReaderThread());
        //service.execute(new ReaderTask());
        service.shutdown();
        number = 42;
        //Thread.sleep(100);
        ready = true;
        //模拟线程在这里上下文切换到另一个线程，就发生了只看到ready的修改，
        // 而没有看到number的修改

        // 这会发生指令重排，几乎是必定发生的，不会打印yield
        //new ReaderThread().start();
        //加了这句话就不会发生指令重排，会一直打印yield直到ready和number发生变化
        //Thread.sleep(1000 * 1);
        //ready = true;
        //number = 42;
    }
}
