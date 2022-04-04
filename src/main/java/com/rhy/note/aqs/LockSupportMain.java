package com.rhy.note.aqs;

import java.util.concurrent.locks.LockSupport;

public class LockSupportMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LockSupportTask());
        thread.start();
        Thread.sleep(5000);
        LockSupport.unpark(thread);
    }
}

class LockSupportTask implements Runnable{

    @Override
    public void run() {
        System.out.println("开始执行");
        //检查是否能继续运行，否则阻塞
        LockSupport.park();
        System.out.println("执行完毕");
    }
}
