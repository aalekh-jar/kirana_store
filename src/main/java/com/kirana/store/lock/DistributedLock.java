package com.kirana.store.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    boolean acquireLock(String key, long timeout, TimeUnit unit);
    void releaseLock(String key);
}