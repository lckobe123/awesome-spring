package com.ternence.concurrency;

import com.ternence.concurrency.even.EvenChecker;
import com.ternence.concurrency.even.EvenGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/6
 * @description 主方法测试线程
 * <p>
 * 留心 ' 指令重排' 的问题
 */
public class MainApplication {
    /**
     * start function
     * 好吧 我没测试出来这个问题？如何使其快速的失败呢？
     */
    public static void main(String[] args) {
        System.out.println("Press Crtl-C to exit program");
        System.out.println("可用处理器数:" + Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newCachedThreadPool();
        EvenGenerator generator = new EvenGenerator();
        for (int i = 0; i < 80; i++) {
            service.execute(new EvenChecker(generator, i));
        }
    }
}
