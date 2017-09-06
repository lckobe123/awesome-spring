package com.ternence.concurrency.even;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 定义整数生成器
 */
public abstract class IntGenerator {
    //volatile保证可视性
    private volatile boolean canceled = false;

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        this.canceled = true;
    }

    /**
     * 生产一个整数
     *
     * @return
     */
    abstract int next();
}
