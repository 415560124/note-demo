package com.rhy.note.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition waitSmoke = lock.newCondition();
    private static final Condition waitWork = lock.newCondition();
    public static void main(String[] args) {

        new Thread(() -> {
           lock.lock();
           try{
               System.out.println("等烟ing...");
               waitSmoke.await();
               System.out.println("烟来了");
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }
        }).start();
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("等活ing...");
                waitWork.await();
                System.out.println("活来了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("送烟了");
                waitSmoke.signalAll();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("送活了");
                waitWork.signalAll();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
