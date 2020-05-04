/**   
 * Copyright © 2020 eSunny Info. Tech Ltd. All rights reserved.
 * 作者博客：wadewhy.xyz
 * @Package: xyz.wadewhy.zk.zookeeper_demo1 
 * @author: wadewhy   
 * @date: 2020年5月4日 下午3:48:41 
 * 序列化类
 */
package xyz.wadewhy.zk.zookeeper_demo1;

import java.io.UnsupportedEncodingException;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class CustomerSerializer implements ZkSerializer {

    /** default utf 8 */
    private String charset = "UTF-8";

    public CustomerSerializer() {
        // TODO Auto-generated constructor stub
    }

    public CustomerSerializer(String charset) {
        this.charset = charset;
    }

    /**
     * 序列化
     */
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try {
            byte[] bytes = String.valueOf(data).getBytes(charset);
            return bytes;
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
    }

    /**
     * 反序列化
     */
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String result = null;
        try {
            result = new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
        return result;
    }

}
