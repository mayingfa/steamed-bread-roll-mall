package com.qiu.util.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/1 10:01
 * @description 读取配置文件信息
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final String REDIS_HOST;
    private static final String REDIS_PWD;
    private static final String PORT;
    private static final String DOMAIN;
    private static final String CALLBACK;

    static {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("config" +
                ".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("config.properties File not found!");
        }
        REDIS_HOST = (String) properties.get("redisHost");
        REDIS_PWD = (String) properties.get("redisPwd");
        PORT = (String) properties.get("port");
        DOMAIN = (String) properties.get("domain");
        CALLBACK = (String) properties.get("callback");
    }

    public static String getRedisHost() {
        return REDIS_HOST;
    }

    public static String getRedisPwd() {
        return REDIS_PWD;
    }

    public static String getPort() {
        return PORT;
    }

    public static String getDomain() {
        return DOMAIN;
    }

    public static String getCallback() {
        return CALLBACK;
    }

    private PropertiesUtil() {
    }
}
