package com.ternence.spring.redis;

/**
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class Count {
    private int count = 1;//公共的数据,多个线程一起来争抢,一旦数据被修改为0就不能继续往下修改

    public Count(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;

    }

    public void countSub() {
        if (count > 0) {
            count -= 1;
        } else {
            System.out.println("修改失败了 count=" + count);
        }
    }
}
