package com.ternence.spring.skills.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/5 22:58
 */
public class ASMUsageDemo {
    private final static Logger logger = LoggerFactory.getLogger(ASMUsageDemo.class);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ASMUsageDemo demo = new ASMUsageDemo();
        byte[] classAsData = demo.createHelloWorldMethod();
        String className = "com.ternence.spring.skills.asm.HelloWorld";
        Class objectClass = new MyClassLoader().defineClassForName(className, classAsData);
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

    /**
     * 使用ASM来写一个hello world示例
     */
    private ClassWriter createHelloWorldClass() {
        //不修改类的默认行为
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //申明一个jdk1.8的名为HelloWorld的类
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/ternence/spring/skills/asm/HelloWorld", null,
                "java/lang/Object", null);

        //初始化一个无参构造器
        MethodVisitor noArgsConstructor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",
                null, null);
        //对本地变量执行一个操作,https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-6.html,两个参数是操作指令和操作数
        noArgsConstructor.visitVarInsn(Opcodes.ALOAD, 0);
        //执行父类的无参构造器
        noArgsConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V", false);
        //构造器没有返回值
        noArgsConstructor.visitInsn(Opcodes.RETURN);
        noArgsConstructor.visitMaxs(1, 1);
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
        helloWorldMethod.visitLdcInsn("hello world asm !");
        // 执行println方法（执行的是参数为字符串，无返回值的println函数）
        helloWorldMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V", false);
        helloWorldMethod.visitInsn(Opcodes.RETURN);
        helloWorldMethod.visitMaxs(1, 1);
        helloWorldMethod.visitEnd();
        return helloWorldClassWriter.toByteArray();


    }
}
