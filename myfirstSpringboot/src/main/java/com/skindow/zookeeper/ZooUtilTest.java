package com.skindow.zookeeper;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/8/5.
 */
@Slf4j
public class ZooUtilTest {
    public static void main(String[] args) throws InterruptedException {
//        ZookeeperUtil zt = new ZookeeperUtil("127.0.0.1:2181",2000);
//        zt.createZNode("/Skindow","8月5号测试");
//        zt.createZNode("/Skindow/no_1","test1");
//        List<String> skindow = zt.getChild("/Skindow");
//        String s = skindow.stream().collect(Collectors.joining(",")).toString();
//        log.info("skindow ==>"  + s);
//        String s1 = zt.readData("/Skindow");
//        log.info("s1 ==》" + s1);
//        zt.deteleZKNode("/Skindow/no_1");
//        log.info("delete after => "+ zt.getChild("/Skindow").stream().collect(Collectors.joining(",")).toString());
//        zt.updateZKNodeData("/Skindow","8月5号测试下班");
//        log.info("update after" + zt.readData("/Skindow"));
        ZkWatchTest zt = new ZkWatchTest("127.0.0.1:2181",2000);
//        zt.deteleZKNode("/test_watcher");//不支持递归删除 这一行代码会报错 因为该节点下存在watcher_children节点
        zt.createZNode("/test_watcher","8月6号测试");
        zt.createZNode("/test_watcher/watcher_children","wc_test");
        log.info("watcher_children read data => " + zt.readData("/test_watcher/watcher_children"));
        zt.deteleZKNode("/test_watcher/watcher_children");
        zt.updateZKNodeData("/test_watcher","8月6号测试");
    }
}
