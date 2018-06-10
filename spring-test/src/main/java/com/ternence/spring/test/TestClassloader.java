package com.ternence.spring.test;

/**
 * 测试java的classloader
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/10 13:43
 */
public class TestClassloader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //sun.misc.Launcher$AppClassLoader加载用户classpath上所指定的类库
        //System.out.println(TestClassloader.class.getClassLoader().getClass());
        ClassLoader myClassloader = new MyClassLoader();
        Object o = myClassloader.loadClass("com.ternence.spring.test.MyClass").newInstance();
        System.out.println(o.getClass().getClassLoader());
        //这个类在应用启动的时候已经被加载的了，AppClassLoader负责加
        //载用户classpath下的类，一般这个类加载器就是默认的类加载器
        System.out.println(MyClass.class.getClassLoader());
        //不是同一个类加载器加载的类,所以自然这个类的实例对这个类使用instanceof却不起作用
        System.out.println(o instanceof MyClass);
    }
}
