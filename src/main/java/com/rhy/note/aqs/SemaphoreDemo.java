package com.rhy.note.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info(Thread.currentThread().getName()+"开始执行");
                    //执行业务逻辑
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
