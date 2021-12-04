package com.qiu.config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class RedisConfig {
    public RedisConfig() {
    }

    public RedisConfig(String host, Integer port, String password, Integer timeout, Integer database) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.timeout = timeout;
        this.database = database;
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.database}")
    private Integer database;
}