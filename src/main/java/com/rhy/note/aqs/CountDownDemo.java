package com.rhy.note.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
@Slf4j
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程【{}】准备完毕",Thread.currentThread().getName());
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        log.info("等待线程准备完毕");
        countDownLatch.await();
        log.info("线程都准备完毕");
    }
}
