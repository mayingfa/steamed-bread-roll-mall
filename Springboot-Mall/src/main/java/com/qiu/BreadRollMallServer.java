package com.qiu;

import com.qiu.util.general.PropertiesUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Qiu
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.qiu.dao")	//指定Dao包
public class BreadRollMallServer {
    public static void main(String[] args) {
        //得到配置信息
        try {
            new PropertiesUtil().getProperties();
        } catch (Exception e) { e.printStackTrace(); }
        SpringApplication.run(BreadRollMallServer.class,args);
    }
}
