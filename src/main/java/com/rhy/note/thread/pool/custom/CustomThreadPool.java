package com.rhy.note.thread.pool.custom;

import org.apache.tomcat.util.threads.TaskThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决传统线程池任务处理顺序：核心 => 队列 => 非核心 的情况
 */
public class CustomThreadPool {
    public static void main(String[] args) {
        int maxThreadNum = 100;
        int coreThreadNum = 10;
        CustomWorkerQueue customWorkerQueue = new CustomWorkerQueue(1);
        ThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(coreThreadNum, maxThreadNum, 300, TimeUnit.SECONDS, customWorkerQueue , new TaskThreadFactory("custom-",Boolean.FALSE,Thread.MAX_PRIORITY) ,new CustomRejectedExecutionHandler());
        customWorkerQueue.setThreadPoolExecutor(threadPoolExecutor);
    }
}
class CustomThreadPoolExecutor extends ThreadPoolExecutor{

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
    private final AtomicInteger submittedCount = new AtomicInteger(0);

    public int getSubmittedCount(){
        return submittedCount.get();
    }

    /**
     * 调用run方法之前执行
     * @param t
     * @param r
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //可以修改任务状态
        super.beforeExecute(t, r);
    }
    /**
     * 执行完毕后触发
     * @param r 任务对象
     * @param t 异常
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if(t != null){
            //可以计入失败任务
            t.printStackTrace();
        }
        //可以修改任务状态
        submittedCount.decrementAndGet();
    }

    /**
     * 提交任务
     * 并已提交任务数+1
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        submittedCount.incrementAndGet();
        super.execute(command);
    }

}
/**
 * 自定义工作队列
 * 修改了任务提交时的执行策略：
 * 1.判断最大线程数是否已满，满了再添加到阻塞队列
 * 2.判断当前已提交数量是否小于可用线程数，说明有空闲线程可以直接执行
 * 3.判断当前线程数是否小于最大核心数，如果小于则返回false，线程池后面就可以创建非核心线程
 * 4.不小于最大核心数说明满了，则进入等待队列
 */
class CustomWorkerQueue extends LinkedBlockingQueue<Runnable> {
    private ThreadPoolExecutor threadPoolExecutor;
    public CustomWorkerQueue(){
        super();
    }
    public CustomWorkerQueue(int capacity) {
        super(capacity);
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public boolean offer(Runnable runnable) {

        if(threadPoolExecutor == null){
            return super.offer(runnable);
        }
        //如果当前线程数等于最大线程数，说明满了，则进入等待队列
        if(threadPoolExecutor.getPoolSize() == threadPoolExecutor.getMaximumPoolSize()){
            return super.offer(runnable);
        }
        //如果是自定义的线程池，则先使用非核心线程
        if(threadPoolExecutor instanceof CustomThreadPoolExecutor){
            CustomThreadPoolExecutor customThreadPoolExecutor = (CustomThreadPoolExecutor)threadPoolExecutor;
            //如果当前已提交任务数小于当前线程数，说明有空闲线程，直接提交到队列，后续线程池会直接消费
            if(customThreadPoolExecutor.getSubmittedCount() < threadPoolExecutor.getPoolSize()){
                return super.offer(runnable);
            }
        }
        //如果当前线程数，小于最大线程数说明还能创建线程，返回false会创建非核心线程
        if(threadPoolExecutor.getPoolSize() < threadPoolExecutor.getMaximumPoolSize()){
            return false;
        }
        return super.offer(runnable);
    }
}

/**
 * 自定义拒绝策略，可以添加到DB等持久层保存
 */
class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //可以保存到数据库等待队列
    }
}