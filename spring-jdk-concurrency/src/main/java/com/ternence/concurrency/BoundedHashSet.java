package com.ternence.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试代码
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public static void main(String[] args) {
        new BoundedHashSet(10);
    }

    public BoundedHashSet(int bound) {
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);//持有bound个许可
    }

    public boolean add(T obj) throws InterruptedException {
        //问题在于这里如果永远不中断怎么办，岂不是一直阻塞，或者当一有元素释放的时候新的元素就加入进来了
        semaphore.acquire();//获取信号量,直到获取到所有的信号量之后就不能继续添加元素了
        boolean wasAdded = false;
        try {
            wasAdded = set.add(obj);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T e) {
        boolean wasRemoved = set.remove(e);
        if (wasRemoved) {
            semaphore.release();//移除一个元素释放一个信号量
        }
        return wasRemoved;

    }
}
