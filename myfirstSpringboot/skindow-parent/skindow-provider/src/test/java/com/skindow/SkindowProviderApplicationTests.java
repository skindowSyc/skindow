package com.skindow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkindowProviderApplication.class)
@Slf4j
public class SkindowProviderApplicationTests {
    @Resource
    private RedissonClient redissonClient;

    @Test
    public void contextLoads() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            myThread skindow = new myThread("skindow");
            skindow.setName("thead_" + i);
            executorService.submit(skindow);
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AllArgsConstructor
    class myThread extends Thread {
        private String name;

        @Override
        public void run() {
            RLock skindow = redissonClient.getLock(name);
            while (true) {
                log.info("{}开始获取锁", Thread.currentThread().getName());
                skindow.lock(10, TimeUnit.SECONDS);
                if (skindow.isLocked()) {
                    log.info("{}获取到锁", Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {

                    }
                    skindow.unlock();
                    log.info("{}释放锁", Thread.currentThread().getName());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
            }


        }
    }
}
