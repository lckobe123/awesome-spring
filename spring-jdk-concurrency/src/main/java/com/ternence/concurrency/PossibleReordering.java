package com.ternence.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 演示jvm指令重排序的demo
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/14 20:18
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    /**
     * 多线程中指令重排会导致计算结果的改变
     * <p>
     * 虽然多线程程序的运算结果难以复现,但是还是存在打印(0,0)的这种可能性
     * <p>
     * 如果没有同步,那么推断出程序的执行顺序是很困难的，非常困难的
     * <p>
     * 同步将会限制编译器，运行时和硬件对内存操作重排序的方式
     */
    public static void main(String[] args) throws InterruptedException {

        Thread aThread = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread bThread = new Thread(() -> {
            b = 1;
            y = a;
        });

        aThread.start();
        bThread.start();
        bThread.join();
        aThread.join();
        System.out.println("x=" + x + ",y=" + y);
    }
}
