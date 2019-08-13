package com.skindow.kafka;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/8/13.
 */
@Slf4j
public class KafkaMain
{
    public static void main(String[] args)
    {
        CountDownLatch latch = new CountDownLatch(1);
        KafkaDemoProducer pro_test_1 = new KafkaDemoProducer.Builder().id("skindow-pro").service("192.168.50.67:9092").times(5000).toptic("skindow-test").latch(latch).builder();
        KafkaDemoConsumer con_test_1 = new KafkaDemoConsumer.Builder().id("skindow-con").service("192.168.50.67:9092").times(5000).toptic("skindow-test").builder();
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(1);
        cachedThreadPool.execute(pro_test_1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("======================");
        log.info("开始执行消费线程");
        ExecutorService cachedThreadPool_1 = Executors.newFixedThreadPool(1);
        cachedThreadPool_1.execute(con_test_1);
    }
}
