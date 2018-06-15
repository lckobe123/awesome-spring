package com.ternence.spring.skills.stream;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * JDK {@link java.util.stream.Stream}功能测试
 * <p>
 * Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
 * 同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，
 * 使用 fork/join 并行方式来拆分任务和加速处理过程。通常编写并行代码很难而且容易出错,
 * 但使用 Stream API 无需编写一行多线程的代码，就可以很方便地写出高性能的并发程序。
 * 所以说，Java 8 中首次出现的 java.util.stream 是一个函数式语言+多核时代综合影响的产物。
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/14 21:55
 */
public class TestJDKStream {

    private static List<String> data;

    @BeforeClass
    public static void init() {
        data = Arrays.asList("1", "2", "3");
    }

    @Test
    public void testStreamFilterAndIterate() {
        Stream<String> stream = data.stream();

        //filter 起到过滤功能,满足条件的返回true，否则返回false,最终结果是一个包含满足条件的元素的Stream
        //forEach是迭代功能
        //每次转换原来的Stream不会变化，会返回一个新的Stream
        stream.filter(s -> Integer.parseInt(s) > 1).forEach(t -> System.out.println("e:" + t));
    }

    @Test
    public void testFlatMap() {

        //JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下。
        //map就是把一个集合中的一个元素映射到另一个元素，然后返回一个映射之后的集合
        //collect(Collectors.toList())将Stream转换成List
        data = data.stream().map(String::toUpperCase)
                .flatMap(new Function<String, Stream<String>>() {
                    @Override
                    public Stream<String> apply(String s) {

                        return null;
                    }
                })
                .collect(Collectors.toList());

        System.out.println(data);
    }
}
