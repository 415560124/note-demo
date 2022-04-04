package com.rhy.note.thread.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(()->{
            synchronized(lock){
                log.info("A get lock！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("A exsits！");
            }
        });
        threadA.start();
        for (int i = 0; i < 55; i++) {
            final int temp = i;
            Thread thread = new Thread(()->{
                synchronized(lock){
                    log.info(temp+" get lock！");
                    log.info(temp+" exsits！");
                }
            });
            thread.start();
        }
    }
}
