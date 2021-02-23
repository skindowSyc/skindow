package com.skindow.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.skindow.test.OracleTOMySql.getAllTableColumn;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 09:46 2021/2/23
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class CoulumnCommentCache {
    //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
    public static LoadingCache<String, Map<String, String>> build;

    public static CacheBuilder<Object, Object> recordStats
            //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
            = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置写缓存后8秒钟过期
            .expireAfterWrite(24, TimeUnit.HOURS)
            //设置写缓存后1秒钟刷新
            .refreshAfterWrite(60, TimeUnit.SECONDS)
            //设置缓存容器的初始容量为5
            .initialCapacity(5)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(100)
            //设置要统计缓存的命中率
            .recordStats();

    public static void initCache() {
        if (Objects.isNull(build)){
            build = recordStats.build(new CacheLoader<String, Map<String, String>>() {
                @Override
                public Map<String, String> load(String key) throws Exception {
                    System.out.println("初始化缓存");
                    return getAllTableColumn();
                }
            });
        }
    }

    public static void main(String[] args) {
        while (true){
            new Thread(() -> {
                initCache();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
