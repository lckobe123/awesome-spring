package com.ternence.concurrency.even;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 产生偶数
 */
public class EvenGenerator extends IntGenerator {
    private int currentEventValue = 80000000;

    @Override
    int next() {
        ++currentEventValue;//danger
        Thread.currentThread().yield();
        ++currentEventValue;//danger
        return currentEventValue;
    }
}
