package com.ternence.concurrency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * {@link java.util.concurrent.ConcurrentHashMap}的使用示例
 */
public class ConcurrentHashMapApplication {

    /**
     * 并发的Map
     */
    private ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 随机数产生器
     */
    private Random random = new Random();

    /**
     * 普通的容器
     */
    private Map<String, Object> map = new HashMap<>();

    /**
     * 启动器
     */
    private final CountDownLatch starter = new CountDownLatch(1);

    private final Runnable writeTask = () -> {
        try {
            System.out.println(Thread.currentThread().getName() + "ready");
            starter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "start");
        for (int i = 0; i < 10; i++) {
            concurrentHashMap.put(Thread.currentThread().getName() + i, random.nextInt());
        }
        //这句打印相当于一个读,如果这里使用的是HashMap,那么将会得到一个异常ConcurrentModificationException
        System.out.println("ConcurrentHashMap after concurrency write :" + concurrentHashMap);
        //这里写入的数量每次都是160
        System.out.println("ConcurrentHashMap size is :" + concurrentHashMap.size());
    };

    public static void main(String[] args) throws InterruptedException {
        new ConcurrentHashMapApplication().write();
    }

    /**
     * 通常情况下ConcurrentHashMap可以支持16个线程的写(理想情况下，其实多余16个线程也是OK的),无数线程的读
     */
    public void write() throws InterruptedException {
        for (int i = 0; i < 16; i++) {
            Thread thread = new Thread(writeTask);
            thread.setName("Thread+" + i);
            thread.start();
        }
        starter.countDown();//开始执行任务

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 测试ConcurrencyHashMap的安全迭代特性,若一致性的迭代器
     * 而非HashMap则是使用了快速失败策略，当迭代过程中发生修改之后将会抛出java.util.ConcurrentModificationException异常
     * <p>
     * When we try to run the below method,output is
     * <p>
     * "Exception in thread "main" java.util.ConcurrentModificationException"
     * 抛出这个异常的方法是iterator.next()方法，而并不是你插入元素这个方法，这就是HashMap的快速失败策略
     * 元素实际上已经插入了Map中，但是会在下一次调用iterator.next()方法的时候才会抛出这个异常，通过iterator抛出
     * 即使使用for-each也是一样的结果,因为也要实现Iterable接口才能使用for-each
     */
    private void iterator() {
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(String.valueOf(i), Math.random() * 100);
            map.put(String.valueOf(i), Math.random() * 100);
        }
        System.out.println("ConcurrencyHashMap before iterator :" + concurrentHashMap);
        Iterator<String> chmIterator = concurrentHashMap.keySet().iterator();
        while (chmIterator.hasNext()) {
            String key = chmIterator.next();
            if (key.equals("3")) {
                concurrentHashMap.put("new-" + key, Math.random() * 100);
            }
        }
        System.out.println("ConcurrencyHashMap after iterator :" + concurrentHashMap);

        //Will be throw exception java.util.ConcurrentModificationException
        //fast fail strategy
        System.out.println("ConcurrencyHashMap before iterator :" + map);
        Iterator<String> mapIterator = map.keySet().iterator();
        while (mapIterator.hasNext()) {
            String key = mapIterator.next();
            System.out.println(key);
            if (key.equals("3")) {
                map.put("new-" + key, Math.random() * 100);
                //这种操作也不会触发ConcurrentModificationException,修改旧的值不会改变modCount
                //map.put(key, Math.random() * 100);
                //避开ConcurrentModificationException的一种巧妙的方式,因为他不会因为进行下一次的迭代而去调用next方法
                //break;
            }
        }
        //Same as above
        /*for (String key : map.keySet()) {
            if (key.equals("3")) {
                map.put("new-" + key, Math.random() * 100);
            }
        }*/
        System.out.println("ConcurrencyHashMap after iterator :" + map);
    }
}
