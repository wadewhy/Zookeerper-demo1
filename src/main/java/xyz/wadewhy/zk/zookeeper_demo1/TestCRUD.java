/**   
 * Copyright © 2020 eSunny Info. Tech Ltd. All rights reserved.
 * 作者博客：wadewhy.xyz
 * @Package: xyz.wadewhy.zk.zookeeper_demo1 
 * @author: wadewhy   
 * @date: 2020年5月4日 下午3:20:35 
 */
package xyz.wadewhy.zk.zookeeper_demo1;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class TestCRUD {

    private static final String SERVERSTARING = "192.168.111.128:2181,192.168.111.128:2182,192.168.111.128:2183";
    private static ZkClient zkClient = null;
    static {
        zkClient = new ZkClient(SERVERSTARING, 10000, 100000);
    }

    public static void main(String[] args) {
        // 创建 create
        /**
         * CreateMode.PERSISTENT持久化目录节点
         */
        zkClient.delete("/java");
        zkClient.create("/java", "java", CreateMode.PERSISTENT);
        System.out.println("创建节点成功");
        // 修改
        zkClient.writeData("/java", "writeDataJava");
        System.out.println("修改节点成功");
        Object readData = zkClient.readData("/java");
        System.out.println(readData);
        zkClient.delete("/java");
        System.out.println("删除节点成功");
    }

}
