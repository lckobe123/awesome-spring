package com.ternence.concurrency.even;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 线程同步的偶数生成器
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEventValue = 2;

    /**
     * 使用同步标记这个方法之后，访问这个方法就会变成串行访问
     *
     * @return
     */
    @Override
    public synchronized int next() {
        ++currentEventValue;//danger
        ++currentEventValue;//danger
        return currentEventValue;
    }
}
