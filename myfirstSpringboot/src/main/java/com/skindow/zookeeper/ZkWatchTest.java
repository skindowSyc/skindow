package com.skindow.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

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
    /**
     * 创建节点
     * @param path 节点路径
     * @param data  节点内容
     * @return
     */
    public boolean createZNode(String path,String data) {

        if (this.isExists(path))
        {
            log.info("该节点已存在请勿再次创建");
            return true;
        }
        //具体状态码
//        String params = "123";
//        CountDownLatch count = new CountDownLatch(1);
        try {
//            Consumer<WatchedEvent> watchedEventConsumer = (WatchedEvent watchedEvent) -> {
//                zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
//                    public void processResult(int rc, String path, Object ctx, String name) {
//                        log.info("Object ctx 参数对应于create 方法最后一个参数");
//                        log.info(ctx.toString());
//                        KeeperException.Code code = KeeperException.Code.get(rc);
//                        log.info("code" + code.toString());
//                        switch (code) {
//                            case OK:
//                                count.countDown();
//                            case NOAUTH:
//                            case NONODE:
//                            case APIERROR:
//                            case NOTEMPTY:
//                            case AUTHFAILED:
//                            case BADVERSION:
//                            case INVALIDACL:
//                            case NODEEXISTS:
//                            case NOTREADONLY:
//                            case SYSTEMERROR:
//                            case BADARGUMENTS:
//                            case SESSIONMOVED:
//                            case UNIMPLEMENTED:
//                            case CONNECTIONLOSS:
//                            case SESSIONEXPIRED:
//                            case INVALIDCALLBACK:
//                            case MARSHALLINGERROR:
//                            case OPERATIONTIMEOUT:
//                            case DATAINCONSISTENCY:
//                            case RUNTIMEINCONSISTENCY:
//                            case NOCHILDRENFOREPHEMERALS:
//                        }
//                    }
//                }, params);
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    log.error("TimeUnit Sleep error => ", e);
//                }
//            };
//            count.await();
            String zkPath = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("Zookeeper创建节点成功，节点地址：" + zkPath);
            return true;
        }catch (Exception e)
        {
            log.error("创建节点失败",e);
        }
        return false;
    }
    /**
     * 删除一个节点
     * @param path 节点路径
     * @return
     */
    public boolean deteleZKNode(String path){
        try{
            zk.delete(path,-1);
            log.info("Zookeeper删除节点成功，节点地址：" + path);
            return  true;
        }catch (InterruptedException e){
            log.error("删除节点失败：" + e.getMessage() + ",path:" + path,e);
        }catch (KeeperException e){
            log.error("删除节点失败：" + e.getMessage() + ",path:" + path,e);
        }
        return false;
    }
    /**
     * 更新节点内容
     * @param path 节点路径
     * @param data 节点数据
     * @return
     */
    public boolean updateZKNodeData(String path,String data){
        try {
            Stat stat = zk.setData(path,data.getBytes(),-1);
            log.info("更新节点数据成功，path:" + path+", stat:" + stat);
            return  true;
        } catch (KeeperException e) {
            log.error("更新节点数据失败：" + e.getMessage() + "，path:" + path ,e);
        } catch (InterruptedException e) {
            log.error("更新节点数据失败：" + e.getMessage() + "，path:" + path ,e);
        }
        return false;
    }

    /**
     * 读取指定节点的内容
     * @param path 指定的路径
     * @return
     */
    public String readData(String path){
        String data=null;
        try {
            log.info("wathcer注册getData<=====" + path + "==============>");
            data = new String(zk.getData(path,watchedEvent -> {
                log.info("getData调用  getPath => " + watchedEvent.getPath());
                log.info("getData调用 getState => " + watchedEvent.getState().toString());
                log.info("getData调用 getType => " + watchedEvent.getType().toString());
            },null));
            log.info("读取数据成功，其中path:" + path+ ", data-content:" + data);
        } catch (KeeperException e) {
            log.error( "读取数据失败,发生KeeperException! path: " + path + ", errMsg:" + e.getMessage(), e );
        } catch (InterruptedException e) {
            log.error( "读取数据失败,InterruptedException! path: " + path + ", errMsg:" + e.getMessage(), e );
        }
        return data;
    }
    /**
     * 获取某个节点下的所有节点
     * @param path 节点路径
     * @return
     */
    public List<String> getChild(String path){
        log.info("wathcer注册getChildren<=====" + path + "==============>");
        try {
            List<String> list = zk.getChildren(path,watchedEvent -> {
                log.info("getChildren调用  getPath => " + watchedEvent.getPath());
                log.info("getChildren调用 getState => " + watchedEvent.getState().toString());
                log.info("getChildren调用 getType => " + watchedEvent.getType().toString());
            });
            if(list.isEmpty()){
                log.info(path + "的路径下没有节点");
            }
            return list;
        } catch (KeeperException e) {
            log.error( "读取子节点数据失败,发生KeeperException! path: " + path
                    + ", errMsg:" + e.getMessage(), e );
        } catch (InterruptedException e) {
            log.error( "读取子节点数据失败,InterruptedException! path: " + path
                    + ", errMsg:" + e.getMessage(), e );
        }
        return null;
    }

    /**判断路劲节点是否存在
     * @param path
     * @return
     */
    public boolean isExists(String path){
        try {
            log.info("wathcer注册exists<=====" + path + "==============>");
            Stat stat = zk.exists(path,watchedEvent -> {
                log.info("exists调用  getPath => " + watchedEvent.getPath());
                log.info("exists调用 getState => " + watchedEvent.getState().toString());
                log.info("exists调用 getType => " + watchedEvent.getType().toString());
            });
            return null != stat;
        } catch (KeeperException e) {
            log.error( "读取数据失败,发生KeeperException! path: " + path
                    + ", errMsg:" + e.getMessage(), e );
        } catch (InterruptedException e) {
            log.error( "读取数据失败,发生InterruptedException! path: " + path
                    + ", errMsg:" + e.getMessage(), e );
        }
        return  false;
    }
}
