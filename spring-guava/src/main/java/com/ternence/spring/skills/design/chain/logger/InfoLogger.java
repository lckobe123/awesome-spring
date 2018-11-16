package com.ternence.spring.skills.design.chain.logger;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 18:04
 */
public class InfoLogger implements Logger {
    private Logger nextLogger;

    @Override
    public void log(String level, String message) {
        if (INFO.equals(level)) {
            System.out.println("info [" + message + "]");
            return;
        }
        nextLogger.log(level, message);
    }

    @Override
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
}
