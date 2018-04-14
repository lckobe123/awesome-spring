package com.ternence.concurrency;

/**
 * 线程相关的一些demo
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/14 20:13
 */
public class ThreadDemo {

    private Runnable printTask = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println("A" + i);
        }
    };

    private Runnable bPrintTask = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println("B" + i);
        }
    };


    public static void main(String[] args) throws InterruptedException {
        new ThreadDemo().testJoin();
    }

    public void testJoin() throws InterruptedException {
        Thread aThread = new Thread(printTask);
        Thread bThread = new Thread(bPrintTask);
        aThread.start();
        aThread.join();//申请CPU资源，让aThread完成之后才继续之后的代码执行

        System.out.println("B thread to be starting");
        bThread.start();
    }
}
