package com.rhy.note.thread.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(()->{
            synchronized(lock){
                log.info("A get lock！");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("A exsits！");
            }
        });

        Thread threadB = new Thread(()->{
            synchronized(lock){
                log.info("B get lock！");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();
                log.info("B exsits！");
            }
        });

        Thread threadC = new Thread(()->{
            synchronized(lock){
                log.info("C get lock！");
                log.info("C exsits！");
            }
        });
        threadA.start();
        Thread.sleep(100);
        threadB.start();
        Thread.sleep(100);
        threadC.start();
    }
}
