package com.ternence.spring.test.reenter;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/23 12:05
 */
public class LoggingWidget extends Widget {

    public static void main(String[] args) {
        Widget widget = new LoggingWidget();
        widget.doSomething();
    }

    /**
     * 理性理解重写，重写不是你理解的重写，他只不是子类的一个方法，
     * 和父类的哪一个方法其实是完全两个不同的方法，他们可以分别调用，
     * Java并不会因为你调用了父类的方法而去调用子类的实现，或者在你调用子类的方法的时候
     * 顺带调用父类的实现，多态只是在编译时候按照父类的接口调用方法，而在运行的时候则是
     * 按照真实的被new的对象来调用方法，super关键字只是一个语法糖，用来调用父类的同名方法的，
     * this关键字还是遵循本来的意义，他就是调用方法的哪个对象，即使用super.doSomething(),
     * this指向的也不是super,而是你new的哪个对象，在一个方法内部调用的本类的或继承的实例方法默认this就是当前类的
     * 当前调用对象。
     */
    @Override
    public synchronized void doSomething() {

        System.out.println("Subclass's doSomething print this is :" + this);

        super.doSomething();
    }
}
