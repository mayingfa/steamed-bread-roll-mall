package com.qiu.controller;

import com.qiu.config.UserRealm;
import com.qiu.entity.Role;
import com.qiu.entity.User;
import com.qiu.service.RoleService;
import com.qiu.service.UserService;
import com.qiu.util.general.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/6 17:10
 * @description 登录、退出、修改个人信息等业务操作
 */
@Slf4j
@CrossOrigin
@RestController
public class OperateController {
    private static final String EMAIL_FLAG = "@";

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 登录操作
     *
     * @param username   用户登录的账号
     * @param password   用户登录的密码
     * @param rememberMe shiro框架的 记住我 功能
     */
    @RequestMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public CommonResult doLogin(String username, String password, boolean rememberMe) {
        try {
            if (username == null) {
                throw new AuthenticationException();
            }
            if (password == null) {
                throw new IncorrectCredentialsException();
            }
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            if (subject.isAuthenticated()) {
                return CommonResult.error("已登录");
            }
            subject.login(token);
            Map<String, Object> info = new HashMap<>(4);
            String authorization = (String) subject.getSession().getId();
            User user;
            if (username.contains(EMAIL_FLAG)) {
                //包含@符号，代表用户通过邮箱账号登录
                user = userService.selectByKey(username);
            } else {
                //不包含@符号，代表用户通过手机号登录
                user = userService.selectByPhone(username);
            }
            info.put("user", user);      //存放用户信息
            //更新最后登录时间
            user.setLoginTime(new Date());
            userService.updateById(user);
            //存放sessionId, 即 token
            info.put("sessionId", authorization);
            List<Role> roles = roleService.selectAll();
            List<String> rs = new ArrayList<>();    // rs 存放的是role的名称
            List<String> rsInfo = new ArrayList<>();    //rsInfo 存放role的描述
            for (Role role : roles) {
                rs.add(role.getRoleName());
                rsInfo.add(role.getRoleName());
            }
            boolean[] booleans = subject.hasRoles(rs);
            for (int i = booleans.length - 1; i >= 0; i--) {
                if (!booleans[i]) {
                    rs.remove(i);
                    rsInfo.remove(i);
                }
            }
            info.put("role", rs);
            info.put("roleInfo", rsInfo);
            return CommonResult.success("登录成功", info);
        } catch (LockedAccountException e) {
            return CommonResult.error("您的帐号存在异常行为，已被封停（请联系工作人员）");
        } catch (IncorrectCredentialsException e) {
            return CommonResult.error("密码错误,请重新输入");
        } catch (AuthenticationException e) {
            return CommonResult.error("该用户不存在");
        }
    }

    /**
     * 退出登录操作
     *
     * @param key     用户登录的账号
     * @param session 前端存储的session（token）
     */
    @RequestMapping(value = "/logout")
    public CommonResult logout(String key, String session) {
        if (key != null) {
            try {
                User user = userService.selectByKey(key);
                Subject subject = SecurityUtils.getSubject();
                //清除用户缓存
                RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
                UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
                //清空redis中的缓存信息
                userRealm.clearRedis(user, session);
                userRealm.clearCache(SecurityUtils.getSubject().getPrincipals());
                //退出登录
                subject.logout();
                return CommonResult.success("退出成功");
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return CommonResult.error("退出失败，未找到用户帐号");
    }

    /**
     * 未登录时，将被转发到此请求
     */
    @RequestMapping("/notLogin")
    public CommonResult toLogin() {
        return new CommonResult(401, "请登录！");
    }

    /**
     * 暂无权限时，将被转发到此请求
     */
    @RequestMapping("/notRole")
    public CommonResult notRole() {
        return new CommonResult(403, "暂无权限！");
    }

    /**
     * 判断key是否存在   目前用于判断邮箱是否被注册过
     *
     * @param email 邮箱号（账号）
     */
    @RequestMapping(value = "/allow/existUser")
    public CommonResult existUser(String email) {
        Boolean isExist = userService.existsWithPrimaryKey(email);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 判断手机号phone是否存在  目前被用于绑定手机号时，确认手机号已被绑定
     *
     * @param telephone 手机号
     */
    @RequestMapping(value = "/allow/existPhone")
    public CommonResult existPhone(String telephone) {
        Boolean isExist = userService.existsWithPrimaryPhone(telephone);
        if (isExist != null) {
            return CommonResult.success("手机号查询成功", isExist);
        }
        return CommonResult.error("手机号查询失败");
    }

    /**
     * 重置密码、找回密码
     *
     * @param account  账号
     * @param password 密码
     */
    @RequestMapping(value = "/allow/resetpwd")
    public CommonResult resetPwd(String account, String password) {
        if (account != null && password != null) {
            SimpleHash md5 = new SimpleHash("MD5", password, ByteSource.Util.bytes(account), 2);
            Integer id = userService.selectIdByKey(account);
            User user = new User();
            user.setUserId(id);
            user.setPassword(md5.toHex());
            if (userService.updateById(user)) {
                return CommonResult.success("重置密码成功", user);
            }
            return CommonResult.error("重置密码失败");
        }
        return CommonResult.error("用户数据不存在");
    }


    /**
     * 注册新用户
     *
     * @param user 用户信息
     */
    @RequestMapping(value = "/allow/add")
    public CommonResult add(User user) {
        if (user.getPassword() != null && user.getUserName() != null) {
            SimpleHash md5 = new SimpleHash("MD5", user.getPassword(), ByteSource.Util.bytes(user.getAccountNumber()), 2);
            user.setPassword(md5.toHex());
            user.setUserState(true);
            if (userService.insertData(user)) {
                log.info("用户添加成功，用户信息：{}", user);
                return CommonResult.success("注册成功", user);
            } else {
                return CommonResult.error("注册失败");
            }
        }
        return CommonResult.error("用户数据不存在");
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    @RequestMapping(value = "/allow/update")
    public CommonResult update(User user) {
        if (userService.updateById(user)) {
            return CommonResult.success("信息保存成功", user);
        }
        return CommonResult.error("信息保存失败");
    }
}
