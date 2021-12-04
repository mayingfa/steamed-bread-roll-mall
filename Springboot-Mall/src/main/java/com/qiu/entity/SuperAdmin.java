package com.qiu.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Dt
 */
@Data
@ConfigurationProperties(prefix = "admin")
@Component
public class SuperAdmin {
    private String email;
    private String userName;
    private String password;
}