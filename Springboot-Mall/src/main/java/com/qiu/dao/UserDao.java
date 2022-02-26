package com.qiu.dao;

import com.qiu.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:26
 * @description user
 */
@Component
public interface UserDao extends BaseDao<User> {
    /**
     * 查询手机号是否存在
     *
     * @param telephone 手机号
     * @return 是否绑定手机号
     */
    Boolean existsWithPrimaryPhone(String telephone);

    /**
     * 查询用户状态
     *
     * @param accountNumber 用户账号
     * @return 账号是否被锁定
     */
    Boolean selectUserState(String accountNumber);

    /**
     * 通过手机号查询用户
     *
     * @param telephone 手机号
     * @return 用户信息
     */
    User selectByPhone(String telephone);
}
