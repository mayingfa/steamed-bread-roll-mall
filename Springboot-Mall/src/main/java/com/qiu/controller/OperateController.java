package com.qiu.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.qiu.entity.Role;
import com.qiu.entity.User;
import com.qiu.service.RoleService;
import com.qiu.service.UserService;
import com.qiu.util.general.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param username 用户登录的账号
     * @param password 用户登录的密码
     */
    @RequestMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public CommonResult doLogin(String username, String password) {
        User user;
        if (username.contains(EMAIL_FLAG)) {
            //包含@符号，代表用户通过邮箱账号登录
            user = userService.selectByKey(username);
        } else {
            //不包含@符号，代表用户通过手机号登录
            user = userService.selectByPhone(username);
        }
        if (user == null) {
            return CommonResult.error("账号不存在");
        }
        String encodePassword = SaSecureUtil.md5BySalt(password, user.getAccountNumber());
        if (!encodePassword.equals(user.getPassword())) {
            return CommonResult.error("用户名或密码错误");
        }
        // 账号被锁定
        if (!user.getUserState()) {
            StpUtil.disable(user.getUserId(), -1);
        }
        StpUtil.login(user.getUserId());
        //更新最后登录时间
        user.setLoginTime(new Date());
        userService.updateById(user);
        //存放用户信息
        Map<String, Object> info = new HashMap<>(4);
        info.put("user", user);
        //存放sessionId, 即 token
        info.put("sessionId", StpUtil.getTokenInfo().getTokenValue());
        List<Role> roles = userService.getRoleList(user.getUserId());
        Set<String> roseNames = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
        Set<String> roseDescribes = roles.stream().map(Role::getRoleDescribe).collect(Collectors.toSet());
        info.put("role", roseNames);
        info.put("roleInfo", roseDescribes);
        return CommonResult.success("登录成功", info);
    }

    /**
     * 注销登录
     */
    @RequestMapping(value = "/logout")
    public CommonResult logout() {
        StpUtil.logout();
        return CommonResult.success("注销成功");
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
            String encodePassword = SaSecureUtil.md5BySalt(password, account);
            Integer id = userService.selectIdByKey(account);
            User user = new User();
            user.setUserId(id);
            user.setPassword(encodePassword);
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
            String encodePassword = SaSecureUtil.md5BySalt(user.getPassword(), user.getUserName());
            user.setPassword(encodePassword);
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
