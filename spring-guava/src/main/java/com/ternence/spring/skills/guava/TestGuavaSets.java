package com.ternence.spring.skills.guava;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 测试guava的sets操作
 *
 * @author TAOJIANGHANG
 * @version 2.0
 * @since 2018/6/15 16:36
 */
public class TestGuavaSets {

    public static void main(String[] args) {
        intersection();
    }

    /**
     * 交集 两个集合中相同的元素
     */
    private static void intersection() {
        Set<String> srcSet = Sets.newHashSet("2017-08-19", "2017-08-20");
        Set<String> targetSet = Sets.newHashSet("2017-08-19", "2017-08-18");
        Sets.SetView<String> diff = Sets.intersection(srcSet, targetSet);
        diff.forEach(e -> System.out.printf("相同的元素为::%s%n", e));

        //Sets.cartesianProduct(srcSet, targetSet);
    }

    /**
     * 两个集合交集的补集 利用此特性可以做对账的比较，比如比较我方比对方多出的单,和对方比我方多出的单
     */
    private static void diff() {
        Set<String> srcSet = Sets.newHashSet("2017-08-19", "2017-08-20");
        Set<String> targetSet = Sets.newHashSet("2017-08-10", "2017-08-18");

        /*比较两个set集合的差异,返回第一个set存在，第二个set不存在的元素集合*/
        Sets.SetView<String> srcDiff = Sets.difference(srcSet, targetSet);
        /*比较两个set集合的差异,返回第一个set存在，第二个set不存在的元素集合*/
        Sets.SetView<String> targetDiff = Sets.difference(targetSet, srcSet);

        System.out.printf("srcDiff:%n");
        srcDiff.forEach(e -> System.out.println("差异元素是:" + e));

        System.out.printf("targetDiff:%n");
        targetDiff.forEach(e -> System.out.println("差异元素是:" + e));
    }
}
