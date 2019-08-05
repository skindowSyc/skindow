package com.skindow.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/8/5.
 * 关于CreateMode属性解读
 * PERSISTENT                持久化节点
 PERSISTENT_SEQUENTIAL     顺序自动编号持久化节点，这种节点会根据当前已存在的节点数自动加 1
 EPHEMERAL                 临时节点， 客户端session超时这类节点就会被自动删除
 EPHEMERAL_SEQUENTIAL      临时自动编号节点
 ACL 权限
 ZooKeeper 支持以下权限
 CREATE: 能创建子节点
 READ：能获取节点数据和列出其子节点
 WRITE: 能设置节点数据
 DELETE: 能删除子节点
 ADMIN: 能设置权限
 */
@Slf4j
public class ZookeeperUtil {
    private static CountDownLatch cound = new CountDownLatch(1);
    private static volatile Boolean flag = true;
    private String connect;
    private int sessionTimeout;
    private ZooKeeper zk;
    public ZooKeeper getZookeeper() throws InterruptedException {
        final ZooKeeper[] zk = {null};
        new Thread(() -> {
            try {
                zk[0] = new ZooKeeper(connect, sessionTimeout,null);
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
    public ZookeeperUtil(String connect, int sessionTimeout) throws InterruptedException {
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
        try {
            String zkPath = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("Zookeeper创建节点成功，节点地址：" + zkPath);
            return true;
        } catch (KeeperException e) {
            log.error("创建节点失败：" + e.getMessage() + "，path:" + path, e);
        } catch (InterruptedException e) {
            log.error("创建节点失败：" + e.getMessage() + "，path:" + path, e);
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
            data = new String(zk.getData(path,false,null));
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
        try {
            List<String> list = zk.getChildren(path,false);
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
            Stat stat = zk.exists(path,false);
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
