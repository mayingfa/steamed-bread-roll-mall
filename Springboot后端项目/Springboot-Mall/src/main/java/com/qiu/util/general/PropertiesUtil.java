package com.qiu.util.general;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/1 10:01
 * @description 读取配置文件信息
 */
public class PropertiesUtil {
    private static String way = null;
    private static String localHost = null;
    private static String serverHost = null;
    private static String port = null;
    private static String domain = null;
    private static String callback = null;

    public void getProperties() throws Exception{
        Properties properties=new Properties();
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new Exception("File not found!");
        }
        way = (String) properties.get("way");
        localHost = (String) properties.get("localHost");
        serverHost = (String) properties.get("serverHost");
        port = (String) properties.get("port");
        domain = (String) properties.get("domain");
        callback = (String) properties.get("callback");
    }

    public static String getWay() {
        return way;
    }

    public static String getLocalHost() {
        return localHost;
    }

    public static String getServerHost() {
        return serverHost;
    }

    public static String getPort() {
        return port;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getCallback() {
        return callback;
    }
}
