package com.ternence.spring.skills.zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * zookeeper实现的共享锁
 */
public class ShareLock {
    private InterProcessMutex interProcessMutex =
            new InterProcessMutex(null, "/lock");

    public boolean tryLock() {
        try {
            interProcessMutex.acquire();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void release() {
        try {
            interProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
