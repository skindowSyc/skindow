package com.skindow.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/8/6.
 */
@Slf4j
public class ZkWatchTest {
    private static CountDownLatch cound = new CountDownLatch(1);
    private static volatile Boolean flag = true;
    private String connect;
    private int sessionTimeout;
    private ZooKeeper zk;
    public ZooKeeper getZookeeper() throws InterruptedException {
        final ZooKeeper[] zk = {null};
        new Thread(() -> {
            try {
                zk[0] = new ZooKeeper(connect, sessionTimeout, new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        log.info("watchedEvent.getPath(); => "+ watchedEvent.getPath());
                        log.info("watchedEvent.getState(); => "+  watchedEvent.getState());
                        log.info("watchedEvent.getType(); =>" + watchedEvent.getType());
                    }
                });
                while (flag)
                {
                    System.out.println(zk[0].getState() + ".....");
                    Thread.sleep(1000);
                    if (zk[0].getState().equals(ZooKeeper.States.CONNECTING))
                    {
                        log.info("正在连接中。。。");
                    }
                    if (zk[0].getState().equals(ZooKeeper.States.CONNECTED))
                    {
                        log.info("连接成功！");
                        flag = false;
                        cound.countDown();
                    }
                }
            } catch (IOException e) {
                log.error("连接出错：",e);
            } catch (InterruptedException e) {
                log.error("线程错误：",e);
            }
        }).start();
        cound.await();
        return zk[0];
    }
    public ZkWatchTest(String connect, int sessionTimeout) throws InterruptedException {
        this.connect = connect;
        this.sessionTimeout = sessionTimeout;
        zk = getZookeeper();
    }
}
