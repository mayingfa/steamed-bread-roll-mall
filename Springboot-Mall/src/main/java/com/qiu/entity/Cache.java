package com.qiu.entity;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Captain
 */
public class Cache {
    /**
     * 键值对集合
     */
    private static final ConcurrentHashMap<String, List<String>> MAP = new ConcurrentHashMap<>();

    /**
     * 添加缓存
     */
    public static synchronized void put(String key, List<String> data) {
        //清除原键值对
        Cache.remove(key);
        //不设置过期时间
        MAP.put(key, data);
    }

    /**
     * 读取缓存
     */
    public static List<String> get(String key) {
        return MAP.get(key);
    }

    /**
     * 清除缓存
     */
    public static synchronized void remove(String key) {
        MAP.remove(key);
    }

    private Cache() {
    }
}
