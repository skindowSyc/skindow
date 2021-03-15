package com.skindow.AQS;

import java.util.concurrent.CountDownLatch;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 09:32 2021/3/12
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class CountDownTest {

    public static final Integer count = 10;

    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(count);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            myThread myThread = new myThread();
            myThread.setName("test_" + i);
            myThread.start();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " 当前线程挂起");
            COUNT_DOWN_LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count + "个线程全部执行完毕");
    }

    private static class myThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " RUNNING");
            COUNT_DOWN_LATCH.countDown();
            System.out.println(Thread.currentThread().getName() + " END");
        }
    }
}
