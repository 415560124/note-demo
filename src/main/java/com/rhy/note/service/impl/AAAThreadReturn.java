package com.rhy.note.service.impl;

import com.rhy.note.mvc.User;

import java.util.concurrent.*;

/**
* @author: Herion Lemon
* @date: 2021/5/24 20:37
* @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
* @description:
*/
public class AAAThreadReturn implements RunnableFuture<User> {
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public User get() throws InterruptedException, ExecutionException {
        return user;
    }

    @Override
    public User get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return user;
    }

    private User user;
    @Override
    public void run() {
        ///
        ///
        ///
        System.out.println(System.currentTimeMillis());
        ////
        ///
        System.out.println(System.currentTimeMillis());
    }
}
