package com.rhy.note.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println("到达临界点");
    });
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                new Thread(() -> {
                    String name = Thread.currentThread().getName();
                    System.out.println(name+"准备就绪");
                    try {
                        cyclicBarrier.await();
                        System.out.println(name+"开始执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                },String.valueOf(i)+"-"+String.valueOf(j)).start();
            }
        }
    }
}
