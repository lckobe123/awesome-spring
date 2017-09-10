package com.ternence.concurrency.even;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description (线程不安全的偶数生成器)产生偶数
 */
public class ThreadUnsafeEvenGenerator extends IntGenerator {
    private int currentEventValue = 2;

    @Override
    public int next() {
        ++currentEventValue;//danger
        /*try {
            //快速失败的最好验证，线程来到这里之后停一下，
            //别的线程也可以获取这道锁修改currentEventValue，等到这个线程等待过后继续下一步处理，
            //那么他将得到一个被别的线程修改的数据（这样就得到一个错误）
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        //让出CPU
        //Thread.yield();


        ++currentEventValue;//danger
        return currentEventValue;
    }
}
