package com.ternence.concurrency.cancel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 可取消的任务测试代码
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class CancelableTaskDemo {

    public static void main(String[] args) {
        try {
            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(20);
            blockingQueue.put("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();//支持中断机制，会尽最大努力提前结束阻塞状态，响应取消请求
        }


        Thread thread = new Thread(() -> {
            System.out.println("this is a test thread.");
        });
        //中断没有任何语义，如果在取消之外的其他操作中使用中断，那么都是不合适的。
        thread.interrupt();//设置线程的中断标志
        thread.isInterrupted(); //返回线程的中断状态,每个线程都有一个boolean类型的中断标志
        Thread.interrupted();//清除中断状态,并返回之前的值，这是清除中断状态的唯一方式。


        try {
            new Object().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();//wait方法检查了中断标志，支持中断机制
        }
    }
}
