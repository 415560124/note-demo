package com.rhy.note.aqs;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"等待获取【读锁】");
//            System.out.println(Thread.currentThread().getName()+"等待获取【写锁】");
//            readLock.lock();

            writeLock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获取了【读锁】");
//                System.out.println(Thread.currentThread().getName()+"获取了【写锁】");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()+"释放了【读锁】");
//                System.out.println(Thread.currentThread().getName()+"释放了【写锁】");
//                readLock.unlock();
                writeLock.unlock();
            }
        }).start();

        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"等待获取【读锁】");
            System.out.println(Thread.currentThread().getName()+"等待获取【写锁】");
//            readLock.lock();
            writeLock.lock();
            try{
//                System.out.println(Thread.currentThread().getName()+"获取了【读锁】");
                System.out.println(Thread.currentThread().getName()+"获取了【写锁】");
            }finally {
//                System.out.println(Thread.currentThread().getName()+"释放了【读锁】");
                System.out.println(Thread.currentThread().getName()+"释放了【写锁】");
//                readLock.unlock();
                writeLock.unlock();
            }
        }).start();
    }
}
