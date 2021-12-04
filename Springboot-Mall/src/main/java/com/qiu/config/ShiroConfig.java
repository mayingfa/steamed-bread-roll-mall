package com.qiu.config;

import com.qiu.util.general.PropertiesUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/26 15:11
 * @description Shiro框架配置类
 */

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/notLogin");
        factoryBean.setUnauthorizedUrl("/notRole");
        // 存放自定义的filter
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        // 配置自定义 or角色 认证
        filtersMap.put("roles", new RoleOrFilter());
        factoryBean.setFilters(filtersMap);

        Map<String, String> filterChainMap  = new LinkedHashMap<>();
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/index", "anon");
        filterChainMap.put("/allow/**", "anon");
        filterChainMap.put("/order/**", "anon");
        filterChainMap.put("/util/**", "anon");
        filterChainMap.put("/user/**", "anon");
        filterChainMap.put("/role/**", "anon");
        filterChainMap.put("/userRole/**", "anon");
        filterChainMap.put("/productType/**", "anon");
        filterChainMap.put("/product/**","anon");
        filterChainMap.put("/static/**","anon");
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     *  身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return 返回userRealm
     */
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        userRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        userRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        userRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        userRealm.setAuthorizationCacheName("authorizationCache");
        //配置自定义密码比较器
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    /**
     * RedisManager 插件
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(PropertiesUtil.getRedisHost());
        redisManager.setPort(6379);
        redisManager.setTimeout(8000);
        redisManager.setPassword(PropertiesUtil.getRedisPwd());
        return redisManager;
    }


    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * 配置核心安全事务管理器
     * @return securityManager
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置自定义realm.
        securityManager.setRealm(userRealm());
        //配置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        //配置redis缓存
        securityManager.setCacheManager(cacheManager());
        //配置自定义session管理，使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    private SimpleCookie rememberMeCookie() {
        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置 cookie 的过期时间，单位为秒 一个月
        cookie.setHttpOnly(true);
        // 7天
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }

    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie 加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Postag=="));
        return cookieRememberMeManager;
    }


    /**
     * RedisSessionDao 插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    /**
     * Session 管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //将修改的cookie放入sessionManager中
        Cookie cookie = new SimpleCookie("jeesite.session.id");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(86400);
        defaultWebSessionManager.setSessionIdCookie(cookie);
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        //1小时无操作自动过期
        defaultWebSessionManager.setGlobalSessionTimeout((long)3600 * 1000);
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }//60ms =1s  1min = 3600


    /**
     * 开启 shiro aop 注解模式  注意需要注解依赖
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}