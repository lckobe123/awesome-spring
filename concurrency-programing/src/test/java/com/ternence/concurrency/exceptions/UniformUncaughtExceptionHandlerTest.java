package com.ternence.concurrency.exceptions;

import com.ternence.concurrency.tasks.UncaughtExceptionTask;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/3
 * @description 测试未捕获异常处理器的功能
 */
public class UniformUncaughtExceptionHandlerTest {

    @Test
    public void uncaughtException() throws Exception {
        UniformUncaughtExceptionHandler exceptionHandler=
                new UniformUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new UncaughtExceptionTask());
        service.shutdown();
    }

}