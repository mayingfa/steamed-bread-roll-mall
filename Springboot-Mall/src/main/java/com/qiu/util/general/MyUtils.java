package com.qiu.util.general;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/29 20:02
 * @description 自定义工具类
 */

public class MyUtils {
    public static String getCode(int length) {
        String str = String.valueOf(System.currentTimeMillis());
        return str.substring(str.length() - length);
    }

    private MyUtils() {
    }
}
