package com.skindow.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2019/8/13.
 */
@Slf4j
public class KafkaDemoConsumer implements Runnable{
    //消费者唯一标示
    private String id;
    //消费主题
    private String toptic;
    //kafka服务器地址和端口
    private String service;
    //配置属性
    private Properties props;
    private volatile Boolean flag = true;
    //消费者消息数量
    private AtomicInteger i = new AtomicInteger(0);
    //消费间隔
    private long times;
    public KafkaDemoConsumer(Builder builder){
        this.toptic = builder.toptic;
        this.service = builder.service;
        this.id = builder.id;
        this.times = builder.times;
        this.initProperties();
    }
    public KafkaConsumer<String,String> getKafkaConsumer()
    {
        return new KafkaConsumer<String, String>(props);
    }
    public void initProperties()
    {
        props = new Properties();
        //程序的唯一标识符以区别于其他应用程序与同一Kafka集群通信
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, this.id);
        //用于建立与Kafka集群的初始连接的主机/端口对的列表
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.service);
        //用来唯一标识consumer进程所在组的字符串
        props.put("group.id", "skindow");
        //如果为真，consumer所fetch的消息的offset将会自动的同步到zookeeper
        props.put("enable.auto.commit", "true");
        //consumer向zookeeper提交offset的频率，单位是秒
        props.put("auto.commit.interval.ms", "1000");
        //提供键值对的反序列化
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static class Builder{
        private String toptic;
        private String service;
        private String id;
        private long times;
        public Builder toptic(String toptic)
        {
            this.toptic = toptic;
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
        public KafkaDemoConsumer builder()
        {
            return new KafkaDemoConsumer(this);
        }
    }
    @Override
    public void run() {
        while (flag) {
            log.info(id + "开始消费主题" + toptic);
            try {
                Thread.sleep(times);
            } catch (InterruptedException e) {
                log.error("Thread sleep error: ", e);
            }
            KafkaConsumer<String, String> kafkaConsumer = getKafkaConsumer();
            kafkaConsumer.subscribe(Arrays.asList(toptic));
            Duration duration = Duration.ofMillis(500);
            //频率
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(duration);
            for (ConsumerRecord<String, String> record : poll)
            {
                log.info("======================");
                log.info(id + " 消费者offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
