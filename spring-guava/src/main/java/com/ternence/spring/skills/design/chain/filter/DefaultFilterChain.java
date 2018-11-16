package com.ternence.spring.skills.design.chain.filter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 17:58
 */
public class DefaultFilterChain implements FilterChain {

    private final static List<Filter> FILTERS = new CopyOnWriteArrayList<>();

    /**
     * 当前执行的filter的位置
     */
    private volatile AtomicInteger pos = new AtomicInteger(0);

    @Override
    public void doFilterChain(String req, String resp) {

    }
}
