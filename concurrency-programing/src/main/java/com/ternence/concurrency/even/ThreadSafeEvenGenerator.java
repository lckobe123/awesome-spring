package com.ternence.concurrency.even;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 线程安全的偶数生成器
 */
public class ThreadSafeEvenGenerator extends IntGenerator {
    //currentEventValue对象是共享的，只有一个
    //但是在ThreadLocal内部会有一个ThreadLocalMap来维护使用这个值的线程和这个值的一个一一对应
    private ThreadLocal<Integer> currentEventValue = ThreadLocal.withInitial(() -> 0);

    @Override
    public int next() {
        currentEventValue.set(currentEventValue.get() + 1);
        currentEventValue.set(currentEventValue.get() + 1);
        return currentEventValue.get();
    }
}
