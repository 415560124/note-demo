package com.rhy.note.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingDeque();
        new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                try {
                    System.out.println("生产元素"+i);
                    blockingQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                try {
                    Object take = blockingQueue.take();
                    System.out.println("消费元素"+take);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
