package com.ternence.jdk.utils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/9 11:05
 * <p>
 * 遗憾的是，在绝大多数Java代码里，我们常常忘记了判断空引用，如何优雅的解决这个问题？
 * Optional是对可以为空的对象进行的封装, 并不会减少代码量，甚至比原来的代码还多。但好处在于，
 * 你绝对不会忘记判空，因为这里我们得到的不是一个你期望得到的对象，而是Optional，他的get方法可能返回null
 * Optional给了我们一个有意义的null,可交互的null
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> result = doSomething(args);

        System.out.println("--------------------------- Section one ---------------------------");
        //如果你这样写的话和 直接判断 text == null 没有太大的区别
        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("变量为null");
        }


        System.out.println("--------------------------- Section two ---------------------------");
        //可以自己处理返回值为null时候抛出的异常，也可以抛出给客户端自己处理,这个看特定的context下的处理选择
        try {
            System.out.println("变量值为:" + result.orElseThrow(WhereSupplierSaved.getSthThrowSupplier()));
        } catch (Throwable throwable) {
            System.out.println("异常:" + throwable.getMessage());
        }

        System.out.println("--------------------------- Section three ---------------------------");
        try {
            System.out.println(result.orElseGet(WhereSupplierSaved.getSthSupplier()));
        } catch (RuntimeException e) {
            System.out.println("读取数据发生异常," + e.getMessage());
            return;
        }

        System.out.println("后续操作...");


        //else do nothing
    }

    /**
     * 逻辑就是比如一个方法返回一个List,那么需要返回一个empty的List,让客户端可以正常遍历，而不会出现异常，
     * 同理，如果一个方法返回一个pojo,那么如果直接返回null,那么很可能发生NPE,但是返回Optional的话则至少可以支持让客户选择端安全的访问这个变量的方法
     * 而不是直接访问变量得到一个NPE，让程序发生异常,需要返回null的时候，使用Optional.empty()则可以返回一个空的对象，和null是有区别的。
     * <p>
     * 你可以将Optional看成是T值使用者与T值之间中间人或者协调者(Mediator),他提供可交互的方式来安全的访问T值，而不会导致意外的NPE异常，更好的
     * 可读性和交互性,而且可以优雅的表达一个null值,方法可以满足一个输入一个输出，而不会是一个输入得到两种可能的输出(异常或正确输出)
     */
    private static Optional<String> doSomething(String[] args) {
        if (args != null) {
            return Stream.of(args).findFirst();
        }
        return Optional.empty();
    }
}
