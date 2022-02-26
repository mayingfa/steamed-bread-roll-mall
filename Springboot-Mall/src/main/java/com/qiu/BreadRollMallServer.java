package com.qiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Captain
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.qiu.dao")
public class BreadRollMallServer {
    public static void main(String[] args) {
        SpringApplication.run(BreadRollMallServer.class,args);
    }
}
