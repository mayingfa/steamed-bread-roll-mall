package com.qiu.dao;

import com.qiu.dao.BaseDao;
import com.qiu.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:26
 * @description user 的 dao层接口
 */
@Component
public interface UserDao extends BaseDao<User> {
    //判断手机号telephone是否存在
    Boolean existsWithPrimaryPhone(String telephone);
    //查询用户状态
    Boolean selectUserState(String accountNumber);
    //通过手机号查询用户
    User selectByPhone(String telephone);
}
