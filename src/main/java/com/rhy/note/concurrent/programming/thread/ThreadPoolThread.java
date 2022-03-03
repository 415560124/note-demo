package com.rhy.note.concurrent.programming.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPoolThread implements Runnable{
    private String name;

    public ThreadPoolThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("正在执行任务：【"+Thread.currentThread().getName()+"】");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
