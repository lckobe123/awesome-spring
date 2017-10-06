package com.ternence.spring.drools;

import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * create by Ternence at 2017/10/5 20:39
 *
 * @version 1.0
 * @email ternence.tao@foxmail.com
 * @description 测试ASM生成字节码的功能
 */
public class AsmTests {

    /**
     * 测试一下生成字节码的功能
     */
    @Test
    public void testGenerateClassCode() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //访问类的头部分,创建类申明，包括访问修饰符，类名，实现了的接口，父类等等(1.8是兼容的可以运行1.6的字节码)
        classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC,
                "com/ternence/spring/drools/ASMClassCode",
                null, "java/lang/Object", null);
        //访问方法，创建类的构造器
        MethodVisitor construct = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>", "()V", null, null);
        construct.visitCode();
        //访问变量指令（loads or stores ）
        construct.visitVarInsn(Opcodes.ALOAD, 0);
        construct.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V");
        //这个很重要
        construct.visitInsn(Opcodes.RETURN);
        //访问关于栈的参数
        construct.visitMaxs(0, 0);
        //所有访问方法的操作都需要调用这个方法
        construct.visitEnd();

        //构造字节码
        final byte[] code = classWriter.toByteArray();
        Class asmObjClass = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {

                return defineClass(name, code, 0, code.length);
            }
        }.loadClass("com.ternence.spring.drools.ASMClassCode");
        //构造类的实例
        Object asmObj = asmObjClass.newInstance();

        System.out.println(asmObj);
    }
}
