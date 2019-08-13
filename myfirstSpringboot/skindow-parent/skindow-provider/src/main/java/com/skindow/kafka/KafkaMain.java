package com.skindow.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/8/13.
 */
public class KafkaMain
{
    public static void main(String[] args)
    {
        KafkaDemoProducer pro_test_1 = new KafkaDemoProducer.Builder().id("skindow").service("192.168.50.67:9092").times(10000).toptic("skindw-producer").builder();
        KafkaDemoConsumer con_test_1 = new KafkaDemoConsumer.Builder().id("skindow").service("192.168.50.67:9092").times(10000).toptic("skindw-producer").builder();
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(1);
        cachedThreadPool.execute(pro_test_1);
        ExecutorService cachedThreadPool_1 = Executors.newFixedThreadPool(1);
        cachedThreadPool_1.execute(con_test_1);
    }
}
