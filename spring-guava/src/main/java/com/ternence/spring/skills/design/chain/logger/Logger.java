package com.ternence.spring.skills.design.chain.logger;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 18:03
 */
public interface Logger {
    String DEBUG = "1";
    String INFO = "2";
    String ERROR = "3";

    void log(String level, String message);

    void setNextLogger(Logger nextLogger);
}
