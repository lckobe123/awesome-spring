package com.ternence.spring.skills.design.chain.filter;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 17:54
 */
public interface FilterChain {

    void doFilterChain(String req, String resp);
}
