package com.ternence.spring.skills.design.chain.logger;

/**
 * TODO : add imperative description for the class !
 *
 * @author TAOJIANGHANG
 * @version TODO : set version no.
 * @since 2018/11/16 18:04
 */
public class DebugLogger implements Logger {
    private Logger nextLogger;

    @Override
    public void log(String level, String message) {
        if (INFO.equals(level)) {
            System.out.println("debug [" + message + "]");
            return;
        }
        nextLogger.log(level, message);
    }

    @Override
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
}
