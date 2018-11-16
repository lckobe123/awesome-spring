package com.ternence.spring.skills.design.chain.filter;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 17:49
 */
public interface Filter {

    void doFilter(FilterChain chain, String req, String resp);

}
