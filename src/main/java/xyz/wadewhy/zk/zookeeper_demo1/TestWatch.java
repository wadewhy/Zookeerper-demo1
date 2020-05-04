/**   
 * Copyright © 2020 eSunny Info. Tech Ltd. All rights reserved.
 * 作者博客：wadewhy.xyz
 * @Package: xyz.wadewhy.zk.zookeeper_demo1 
 * @author: wadewhy   
 * @date: 2020年5月4日 下午4:01:13 
 * 测试zk监听
 */
package xyz.wadewhy.zk.zookeeper_demo1;

import java.io.IOException;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class TestWatch {

    private static final String SERVERSTARING = "192.168.111.128:2181,192.168.111.128:2182,192.168.111.128:2183";
    private static ZkClient zkClient = null;
    static {
        zkClient = new ZkClient(SERVERSTARING, 10000, 100000, new CustomerSerializer());
    }

    /**
     * @Title: main
     * @Description: TODO
     * @param args void
     * @author wadewhy
     * @throws IOException
     * @date 2020年5月4日下午4:02:28
     */
    public static void main(String[] args) throws IOException {
        /**
         * 监听当前节点的改变和删除事件
         */

        zkClient.subscribeDataChanges("/java", new IZkDataListener() {
            /**
             * 当dataPath这个节点的数据发生改变时回调用 data:改变这之后的值
             */

            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("监控修改的情况:" + dataPath + "  " + data);
            }

            // 当dataPath被删除时回调用的方法
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("监控删除的情况：" + dataPath);
            }

        });
        // 监听子节的变化
        zkClient.subscribeChildChanges("/sanguo", new IZkChildListener() {

            /**
             * parentPath父节点路径 currentChilds 当前父节点下面的所有子节点
             */
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                for (String string : currentChilds) {

                    System.out.println("监控子节点修改的情况:" + parentPath + "  " + string);
                }
            }

        });

        System.err.println("监控启动成功");
        System.in.read();// 让程序阻塞
    }

}
