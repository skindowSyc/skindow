package com.skindow.zookeeper;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/8/5.
 */
public class ZookeeperTest {
    private static CountDownLatch cound = new CountDownLatch(1);
    private static volatile Boolean flag = true;
    public static void main(String[] args) throws IOException, InterruptedException {
        final ZooKeeper[] zk = {null};
        new Thread(() -> {
            try {
                zk[0] = new ZooKeeper("127.0.0.1:2181",2000,null);
                while (flag)
                {
                    System.out.println(zk[0].getState() + ".....");
                    Thread.sleep(1000);
                    if (zk[0].getState().equals(ZooKeeper.States.CONNECTING))
                    {
                        System.out.println("正在连接中。。。");
                    }
                    if (zk[0].getState().equals(ZooKeeper.States.CONNECTED))
                    {
                        System.out.println("连接成功！");
                        flag = false;
                        cound.countDown();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        cound.await();
        List<String> children;
        if (zk[0]!= null) {
            try {
                String zpath = "/";
                children = zk[0].getChildren(zpath, false);
                System.out.println("Znodes of '/': ");
                for (String child : children) {
                    System.out.println(child);
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
