package com.ternence.concurrency.even;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 测试EvenChecker的工作状态
 */
public class EvenCheckerTest {

    @Test
    public void testEvenChecker() {
        System.out.println("Press Crtl-C to exit program");
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.execute(new EvenChecker(new ThreadUnsafeEvenGenerator(), i));
        }
        service.shutdown();
    }

    @Test
    public void testTryCatch() {
        try {
            System.out.println(1 / 0);
            return ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行finally");
        }
    }

}