package com.rhy.note.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AbstractQueuedSynchronizerImpl extends AbstractQueuedSynchronizer{
    @Override
    protected boolean tryAcquire(int arg) {
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(1);
        return Boolean.TRUE;
    }

    public void lock(){
        acquire(1);
    }
    public boolean tryLock(){
        return tryAcquire(1);
    }
    public void unLock(){
        release(1);
    }

    public boolean tryUnLock(){
        return tryRelease(1);
    }
}
