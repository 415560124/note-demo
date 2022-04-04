package com.rhy.note.aqs;

import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static int sum = 0;
    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.lock();
                try{
                    for (int j = 0; j < 10000; j++) {
                        sum++;
                    }
                }finally {
                    lock.unlock();
                }
            },String.valueOf(i)).start();

        }
    }
}
