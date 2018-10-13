package com.ternence.spring.skills.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;

import java.util.stream.Stream;

/**
 * classloader的基本使用
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/13 12:47
 */
public class TestClassLoader {

    private final static Logger logger = LoggerFactory.getLogger(TestClassLoader.class);

    /**
     * 系统类加载器
     */
    private final static String SYSTEM_CLASSLOADER = "system";
    /**
     * 启动类加载器
     */
    private final static String BOOTSTRAP_CLASSLOADER = "bootstrap";
    /**
     * 应用类加载器
     */
    private final static String APPLICATION_CLASSLOADER = "application";
    /**
     * 扩展类加载器
     */
    private final static String EXT_CLASSLOADER = "ext";

    public static void main(String[] args) throws IllegalAccessException {
        TestClassLoader test = new TestClassLoader();
        ClassLoader systemLoader = test.getClassLoader(SYSTEM_CLASSLOADER);
        ClassLoader appLoader = test.getClassLoader(APPLICATION_CLASSLOADER);
        logger.info("systemLoader : {} appLoader:{}  parent: {} parent-parent: {}", systemLoader, appLoader,
                appLoader.getParent(), appLoader.getParent().getParent());
        logger.info("String Classloader : {}", String.class.getClassLoader());
        if (appLoader == systemLoader) {
            logger.info("appLoader 等于 systemLoader");
        } else {
            logger.info("appLoader 不等于 systemLoader");
        }

        logger.info("thread context class loader: {}", Thread.currentThread().getContextClassLoader());

        logger.info("java.class.path -----------------------");
        Stream.of(System.getProperty("java.class.path").split(";"))
                .forEach(s -> logger.info("{}", s));

        logger.info("sun.boot.class.path ---------------------");
        Stream.of(System.getProperty("sun.boot.class.path").split(";"))
                .forEach(s -> logger.info("{}", s));
    }

    private ClassLoader getClassLoader(String type) throws IllegalAccessException {
        ClassLoader classLoader = null;
        switch (type) {
            case BOOTSTRAP_CLASSLOADER:
                throw new IllegalAccessException("bootstrap classloader不可访问");
                /*这两个值是一样的*/
            case SYSTEM_CLASSLOADER:
            case APPLICATION_CLASSLOADER:
                classLoader = ClassLoader.getSystemClassLoader() == null ?
                        Launcher.getLauncher().getClassLoader() :
                        ClassLoader.getSystemClassLoader();
                break;
            case EXT_CLASSLOADER:
                throw new IllegalAccessException("ext classloader不可访问");
        }
        return classLoader;
    }
}
