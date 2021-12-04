package com.qiu.util.general;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/29 20:02
 * @description 自定义工具类
 */

public class MyUtils {
    public static String getCode(int length){
        String str = String.valueOf(System.currentTimeMillis());
        return str.substring(str.length()-length);
    }

    public static void speedTest(long startTime,long oneTime,long twoTime,long threeTime){
        System.out.println(startTime);
        System.out.println(oneTime);
        System.out.println(twoTime);
        System.out.println(threeTime);

        System.out.println("第一阶段程序完成运行时间：" + (oneTime - startTime) + "ms"); //输出程序运行时间
        System.out.println("第二阶段程序完成运行时间：" + (twoTime - startTime) + "ms"); //输出程序运行时间
        System.out.println("第三阶段程序完成运行时间：" + (threeTime - startTime) + "ms"); //输出程序运行时间
        System.out.println("------------------------------------");
        System.out.println("第一阶段到第二阶段程序运行时间：" + (twoTime - oneTime) + "ms"); //输出程序运行时间
        System.out.println("第二阶段到第三阶段程序运行时间：" + (threeTime - twoTime) + "ms"); //输出程序运行时间
    }
}
