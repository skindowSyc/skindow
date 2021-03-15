package com.skindow.AQS;

import java.util.concurrent.Semaphore;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 16:03 2021/3/12
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class SemaphoreTest {

    public static final int COUNT = 1;

    public static final Semaphore SEMAPHORE = new Semaphore(COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            SemaphoreTest.myThread myThread = new SemaphoreTest.myThread(i);
            myThread.setName("test_" + i);
            myThread.start();
        }
    }

    private static class myThread extends Thread {
        public Integer i;

        public myThread(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                boolean flag = false;
                int j = 0;
                System.out.println(Thread.currentThread().getName() + " 获取共享锁中...");
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + " 已获得共享锁,还剩" + SEMAPHORE.availablePermits() + "个共享锁");
                for (; ; ) {
                    if (getCount() > 0 && flag) {
                        System.out.println(Thread.currentThread().getName() + " 获取共享锁中...");
                        SEMAPHORE.acquire();
                        System.out.println(Thread.currentThread().getName() + " 已获得共享锁,还剩" + SEMAPHORE.availablePermits() + "个共享锁");
                    }
                    flag = true;
                    j++;
                    System.out.println(Thread.currentThread().getName() + " RUNNING");
                    Thread.sleep(5000);
                    if (j == 2) {
                        SEMAPHORE.release();
                        System.out.println(Thread.currentThread().getName() + " 释放当前共享锁...");
                        Thread.sleep(1000);
                        continue;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getCount() {
        return SEMAPHORE.availablePermits();
    }
}
