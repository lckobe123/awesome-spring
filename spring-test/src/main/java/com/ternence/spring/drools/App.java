package com.ternence.spring.drools;

/**
 * Hello world!
 */
public class App {
    /**
     * 测试异常对线程的终止结果,如果异常没有被捕获(try-catch),那么异常会被抛到上一层,知道jvm
     * <p>
     * 那jvm对异常的处理是打印异常信息之后终止线程，但是如果你try-catch之后就只是按照你的方式来处理，而不会终止异常
     * <p>
     * 无论运行时异常或者是普通异常都遵循这个规律
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            try {
                throw new RuntimeException("参数为null");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序还能向下执行");
    }
}
