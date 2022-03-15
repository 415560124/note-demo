package com.rhy.note.thread.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println(longAdder.sum());
    }
}
