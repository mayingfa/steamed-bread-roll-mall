package com.qiu.config;

import com.qiu.entity.Role;
import com.qiu.entity.User;
import com.qiu.entity.UserRole;
import com.qiu.service.RoleService;
import com.qiu.service.UserRoleService;
import com.qiu.service.UserService;
import com.qiu.util.general.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author Captain
 */
public class UserRealm extends AuthorizingRealm {

    private static final String EMAIL_FLAG = "@";

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<UserRole> userRoles = userRoleService.selectByUserId(user.getUserId());
        for (UserRole userRole : userRoles) {
            if (userRole != null) {
                Role role = roleService.selectById(userRole.getRoleId());
                authorizationInfo.addRole(role.getRoleName());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user;
        if (userToken.getUsername().contains(EMAIL_FLAG)) {
            // 包含@符号，代表用户通过邮箱账号登录
            user = userService.selectByKey(userToken.getUsername());
        } else {
            // 不包含@符号，代表用户通过手机号登录
            user = userService.selectByPhone(userToken.getUsername());
        }

        //抛出异常 UnknownAccountException
        if (user == null) {
            return null;
        }

        // 账号被锁定
        if (!user.getUserState()) {
            throw new LockedAccountException();
        }

        //密码认证
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                MyByteSource.Util.bytes(user.getAccountNumber()), getName());
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息 身份集合
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 清除Redis缓存
     *
     * @param user    用户信息
     * @param session session信息
     */
    public void clearRedis(User user, String session) {
        redisTemplate.delete("shiro:session:" + session);
        redisTemplate.delete("shiro:cache:" + user.getAccountNumber());
        redisTemplate.delete("shiro:cache:" + user.getTelephone());
    }
}
