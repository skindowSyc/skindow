package com.skindow.sql;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 16:11 2021/3/10
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class ConditionLockTest {
    public static ReentrantLock lock = new ReentrantLock();
    public static final Integer i = 10;
    public static final AtomicBoolean[] flag = new AtomicBoolean[i];
    public static final Condition condition = lock.newCondition();

    static {
        for (int i1 = 0; i1 < flag.length; i1++) {
            flag[i1] = new AtomicBoolean(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(i, i + 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int j = 0; j < i; j++) {
            executorService.submit(new myThread(j));
        }
        Thread.sleep(10000);
    }

    public static class myThread extends Thread {
        private Integer i;

        myThread(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                while (flag[i].get()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 获取到锁");
                    System.out.println(Thread.currentThread().getName() + " 释放锁进入等待!");
                    if (lock.getQueueLength() == 0){
                        System.out.println("唤醒所有等待的线程");
                        condition.signalAll();
                        System.out.println("0-----------------");
                    }else{
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " 等待状态解除!开始获取锁!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void printLog(){
        Class<? extends ReentrantLock> aClass = lock.getClass();
        try {
            Field sync = aClass.getDeclaredField("sync");
            sync.setAccessible(true);
            AbstractQueuedSynchronizer abstractQueuedSynchronizer = (AbstractQueuedSynchronizer) sync.get(lock);
//            Collection<Thread> queuedThreads = abstractQueuedSynchronizer.getQueuedThreads();
//            for (Thread queuedThread : queuedThreads) {
//                System.out.println(queuedThread.getName());
//            }
            Class<?>[] declaredClasses = abstractQueuedSynchronizer.getClass().getDeclaredClasses();
            for (Class<?> declaredClass : declaredClasses) {
                System.out.println(declaredClass.getName());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
