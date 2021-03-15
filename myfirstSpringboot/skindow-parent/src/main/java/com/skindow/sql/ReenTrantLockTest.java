package com.skindow.sql;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 11:08 2021/3/10
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class ReenTrantLockTest {
    public static ReentrantLock lock = new ReentrantLock();
    public static final AtomicBoolean flag = new AtomicBoolean(false);

    @SuppressWarnings("all")
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            lock.lock();
            for (; ; ) {
                if (flag.get()){
                    lock.unlock();
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " running");
            }
        });
        thread.setName("thread");

        Thread thread_1 = new Thread(() -> {
            lock.lock();
            int i = 0;
            for (; ; ) {
                i ++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " running");
            }
        });
        thread_1.setName("thread_1");
        Thread thread_2 = new Thread(() -> {
            lock.lock();
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " running");
            }
        });
        thread_2.setName("thread_2");
        thread.start();
        thread_1.start();
        thread_2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2s后");
        flag.getAndSet(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁资源释放2s后");
        Class<? extends ReentrantLock> aClass = lock.getClass();
        try {
            Field sync = aClass.getDeclaredField("sync");
            sync.setAccessible(true);
            AbstractQueuedSynchronizer abstractQueuedSynchronizer = (AbstractQueuedSynchronizer) sync.get(lock);
//            Collection<Thread> queuedThreads = abstractQueuedSynchronizer.getQueuedThreads();
//            for (Thread queuedThread : queuedThreads) {
//                System.out.println(queuedThread.getName());
//            }
            abstractQueuedSynchronizer.getClass().getDeclaredClasses();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("正在排队的个数" + lock.getQueueLength());
        thread_1.interrupt();
        thread_2.interrupt();
        LockSupport.unpark(thread_1);
        LockSupport.unpark(thread_2);
        System.out.println(thread_1.interrupted());
        System.out.println(thread_2.interrupted());
        System.out.println("设置中断标志,并解锁");
        System.out.println("中断后排队的个数" + lock.getQueueLength());
    }
}
