package com.skindow.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.streams.StreamsConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2019/8/13.
 * 模拟生产者
 */
@Slf4j
public class KafkaDemoProducer implements Runnable{
    //生产者唯一标示
    private String id;
    //生产主题
    private String toptic;
    //kafka服务器地址和端口
    private String service;
    //配置属性
    private Properties props;
    private volatile Boolean flag = true;
    //生产消息数量
    private AtomicInteger i = new AtomicInteger(0);
    //生产间隔
    private long times;
    private KafkaProducer kafkaProducer;
    private CountDownLatch latch;
    public KafkaDemoProducer(Builder builder){
        this.toptic = builder.toptic;
        this.service = builder.service;
        this.id = builder.id;
        this.times = builder.times;
        this.latch = builder.latch;
        this.initProperties();
        kafkaProducer = new KafkaProducer<String, String>(props);
    }
    public void stop()
    {
        kafkaProducer.flush();
        kafkaProducer.close();
        flag = false;
    }
    public KafkaProducer<String,String> getKafkaProducer()
    {
        return kafkaProducer;
    }
    public Properties getProperties()
    {
        return props;
    }
    public void initProperties()
    {
        props = new Properties();
        //程序的唯一标识符以区别于其他应用程序与同一Kafka集群通信
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, this.id);
        //用于建立与Kafka集群的初始连接的主机/端口对的列表
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.service);
        //提供键值对持久化的序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //同步机制，0表示客户端把消息发送就成功了，1表示在0基础上leader把消息保存在本地磁盘上就表示成功了，不w管follower有没有同步成功
        //all表示在1基础上follower同步成功才算成功
        props.put("acks", "all");
        //在执行不彻底的关机之前，可以成功执行关机的命令数。
        props.put("retries", 0);
        //producer将试图批处理消息记录，以减少请求次数。这将改善client与server之间的性能。这项配置控制默认的批量处理消息字节数。
        props.put("batch.size", 16384);
        //producer组将会汇总任何在请求与发送之间到达的消息记录一个单独批量的请求,producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        //这项设置设定了批量处理的更高的延迟边界
        props.put("linger.ms", 1);
        //producer可以用来缓存数据的内存大小
        props.put("buffer.memory", 33554432);
    }
   public static class Builder{
        private String toptic;
        private String service;
        private String id;
        private long times;
        private CountDownLatch latch;
        public Builder toptic(String toptic)
        {
            this.toptic = toptic;
            return this;
        }
        public Builder latch(CountDownLatch latch)
        {
            this.latch = latch;
            return this;
        }
        public Builder service(String service)
        {
            this.service = service;
            return this;
        }
        public Builder id(String id)
        {
            this.id = id;
            return this;
        }
        public Builder times(long times)
        {
            this.times = times;
            return this;
        }
        public KafkaDemoProducer builder()
       {
            return new KafkaDemoProducer(this);
        }
    }
    @Override
    public void run() {
        while (flag)
        {
            log.info(id + "开始生产主题"+ toptic);
            try {
                Thread.sleep(times);
            } catch (InterruptedException e) {
                log.error("Thread sleep error: ",e);
            }
            String key = id + "-key-" + i;
            String value = id + " Under production" + i;
            kafkaProducer.send(new ProducerRecord<String,String>(toptic, key, value), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    Date date = new Date(recordMetadata.timestamp());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = sdf.format(date);
                    log.info(id + "于"+ format + "生产"+ key +"完成");
                    i.incrementAndGet();
                    log.info(id + "已成功生产" + (recordMetadata.offset() + 1) + "条消息");
                    latch.countDown();
                }
            });

        }
    }
}
