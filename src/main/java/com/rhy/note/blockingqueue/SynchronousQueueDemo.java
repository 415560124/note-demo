package com.rhy.note.blockingqueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    private static final SynchronousQueue SYNCHRONOUS_QUEUE = new SynchronousQueue(true);
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"【开始消费】");
                System.out.println(Thread.currentThread().getName()+"【消费数据】"+take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer1").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"【开始消费】");
                System.out.println(Thread.currentThread().getName()+"【消费数据】"+take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer2").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"【开始生产】"+1);
                put(1);
                System.out.println(Thread.currentThread().getName()+"【生产完毕】"+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"producer1").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"【开始生产】"+3);
                put(3);
                System.out.println(Thread.currentThread().getName()+"【生产完毕】"+3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"producer2").start();
    }
    public static Object take() throws InterruptedException {
        return SYNCHRONOUS_QUEUE.take();
    }
    public static void put(Object value) throws InterruptedException {
        SYNCHRONOUS_QUEUE.put(value);
    }
}
