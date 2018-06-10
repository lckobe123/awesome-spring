package com.ternence.spring.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/10 16:04
 */
public class MyClassLoader extends ClassLoader {


    /**
     * 如果是这种写法则遵循了双亲委派模型，这个类最终不会被这个classloader加载，而是被AppClassLoader加载
     *
     * @param name 类的全限定名字
     * @return 找到的Class对象
     * @throws ClassNotFoundException 类未发现异常
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("load class ");

        if (findLoadedClass(name) != null) {
            System.out.println(name+" has been loaded by some classloader!");
        }

        String classFileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream is = getClass().getResourceAsStream(classFileName);
        if (is != null) {
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                is.close();
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(classFileName + " is not fund !");
            }
        }
        return super.loadClass(name);
    }
}
