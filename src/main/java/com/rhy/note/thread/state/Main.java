package com.rhy.note.thread.state;

import lombok.SneakyThrows;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
//        Thread thread = new Thread(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                Thread.sleep(2000);
//            }
//        });
//        Object sync = new Object();
//        Thread thread = new Thread(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                synchronized (sync){
//                    Thread.sleep(2000);
//                }
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                synchronized (sync){
//                    Thread.sleep(2000);
//                }
//            }
//        });
        System.out.println(thread.getState());
//        thread2.start();
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(100);
        System.out.println(thread.getState());
    }
}
