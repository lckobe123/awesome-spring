package com.ternence.spring.skills.classloader;

/**
 * 自定义的用户classloader
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/13 15:07
 */
public class ClientUserClassLoader extends ClassLoader {

    private byte[] classAsData;

    public ClientUserClassLoader(byte[] classAsData) {
        this.classAsData = classAsData;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (classAsData != null && classAsData.length > 0) {

            return defineClass(name, classAsData, 0, classAsData.length);
        } else {
            throw new ClassNotFoundException("不能加载类" + classAsData);
        }
    }
}
