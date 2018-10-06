package com.ternence.spring.skills.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/5 22:58
 */
public class ASMUsageDemo {
    private final static Logger logger = LoggerFactory.getLogger(ASMUsageDemo.class);
    private static final String CANONICAL_CLASS_NAME_WITHOUT_SUFFIX = "com.ternence.spring.skills.asm.HelloWorld";

    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            InvocationTargetException, IOException {
        ASMUsageDemo demo = new ASMUsageDemo();
        byte[] classAsData = demo.createHelloWorldMethod();
        demo.writeClassToFile(classAsData);
        Class objectClass = new MyClassLoader().defineClassForName(
                CANONICAL_CLASS_NAME_WITHOUT_SUFFIX, classAsData);
        logger.info("start invoke method");
        objectClass.getMethods()[0].invoke(objectClass.newInstance());
        logger.info("end invoke method");
    }

    private static class MyClassLoader extends ClassLoader {
        private MyClassLoader() {
            super(Thread.currentThread().getContextClassLoader());
        }

        private Class<?> defineClassForName(String name, byte[] data) {
            return this.defineClass(name, data, 0, data.length);

        }
    }

    private void writeClassToFile(byte[] classAsData) throws IOException {
        File classFile;
        ClassPathResource resource = new ClassPathResource(getPackagePath(CANONICAL_CLASS_NAME_WITHOUT_SUFFIX)
                + getSimpleClassName(CANONICAL_CLASS_NAME_WITHOUT_SUFFIX));
        if (!resource.exists()) {
            logger.info("HelloWorld.class不存在,创建class");
            classFile = new File(new ClassPathResource(getPackagePath(CANONICAL_CLASS_NAME_WITHOUT_SUFFIX))
                    .getURI().getPath() + getSimpleClassName(CANONICAL_CLASS_NAME_WITHOUT_SUFFIX));
            classFile.createNewFile();
        } else {
            logger.info("HelloWorld.class存在,覆盖class");
            classFile = resource.getFile();
        }
        OutputStream outputStream = new FileOutputStream(classFile, false);
        outputStream.write(classAsData);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 使用ASM来写一个hello world示例
     */
    private ClassWriter createHelloWorldClass() {
        //不修改类的默认行为
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //申明一个jdk1.8的名为HelloWorld的类
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, CANONICAL_CLASS_NAME_WITHOUT_SUFFIX.
                        replace('.', '/'), null,
                "java/lang/Object", null);

        //初始化一个无参构造器
        MethodVisitor noArgsConstructor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",
                null, null);
        //对本地变量执行一个操作,https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-6.html,两个参数是操作指令和操作数
        noArgsConstructor.visitVarInsn(Opcodes.ALOAD, 0);//本地变量表中第0个是什么？是无参构造器吗?
        //执行父类的无参构造器,执行之前必须使构造器成为当前方法，所以有上面的调用，构造器必须调用super(),或者this()来简洁调用super
        //来初始化父类,所以这两个操作是必须的
        //INVOKESPECIAL是调用构造器<init>,私有方法和父类方法的字节码指令
        noArgsConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V", false);
        //return from a void method
        noArgsConstructor.visitInsn(Opcodes.RETURN);
        //操作数栈的深度和本店变量表必须大于一
        noArgsConstructor.visitMaxs(1, 1);
        //结束构造器的定义
        noArgsConstructor.visitEnd();

        return writer;
    }

    private byte[] createHelloWorldMethod() {

        ClassWriter helloWorldClassWriter = createHelloWorldClass();

        MethodVisitor helloWorldMethod = helloWorldClassWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "helloWorld", "()V", null, null);
        //先获取一个java.io.PrintStream对象
        helloWorldMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // 将int, float或String型常量值从常量池中推送至栈顶  (此处将message字符串从常量池中推送至栈顶[输出的内容])
        helloWorldMethod.visitLdcInsn("Hello world ASM !");
        // 执行println方法（执行的是参数为字符串，无返回值的println函数）
        helloWorldMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V", false);
        helloWorldMethod.visitInsn(Opcodes.RETURN);
        helloWorldMethod.visitMaxs(1, 1);
        helloWorldMethod.visitEnd();
        return helloWorldClassWriter.toByteArray();
    }

    private String getPackagePath(String canonicalClassName) {
        if (StringUtils.isEmpty(canonicalClassName)) return "/";
        return canonicalClassName.substring(0, canonicalClassName.lastIndexOf('.') + 1)
                .replace('.', '/');
    }

    private String getSimpleClassName(String canonicalClassName) {
        if (StringUtils.isEmpty(canonicalClassName)) return null;
        return canonicalClassName.substring(canonicalClassName.lastIndexOf('.') + 1) + ".class";
    }
}
