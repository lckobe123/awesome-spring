package com.ternence.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入锁的使用demo
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/3/27 21:46
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();//创建一个ReentrantLock,采用非公平锁的策略
        //无条件获取锁，这种获取锁的方式和synchronized具有相同的语义
        lock.lock();

        try {
            //这种获取锁的方式能够保证在获取锁的同时相应中断请求
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("执行逻辑代码");
        } finally {
            lock.unlock();//如果不使用finally来释放Lock，那么相当于启动了一个定时炸弹，锁永远得不到释放
        }


        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//支持读写分别加锁的锁

    }
}
