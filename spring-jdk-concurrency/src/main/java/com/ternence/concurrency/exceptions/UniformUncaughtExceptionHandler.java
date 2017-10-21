package com.ternence.concurrency.exceptions;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 定义统一的未捕获异常的处理方式，也就是捕获为处理的异常的一个异常处理器
 * uncaughtException（Thread,Throwable）会在线程因未捕获的异常而濒临死亡的时候调用，
 * 这里还可以抢救一下，一面系统崩溃
 */
public class UniformUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + "线程抛出未捕获异常了!" + e.toString());
    }
}
