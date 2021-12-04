package com.qiu.service;

import com.qiu.entity.User;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:55
 * @description user 的 service层接口
 */
public interface UserService {
    //通过ID查询
    User selectById(Integer id);
    //通过Key查询
    User selectByKey(String key);
    //通过手机号查询
    User selectByPhone(String telephone);
    //查询所有
    List<User> selectAll();
    //查询记录个数
    Integer selectCount();
    //查询用户状态
    Boolean selectUserState(String accountNumber);
    //判断帐号是否存在
    Boolean existsWithPrimaryKey(String existUser);
    //判断手机号telephone是否存在
    Boolean existsWithPrimaryPhone(String telephone);
    //查询最大Id
    Integer selectMaxId();
    //通过Key查Id
    Integer selectIdByKey(String key);
    //插入一条数据
    Boolean insertData(User user);
    //通过ID更新
    Boolean updateById(User user);
    //通过ID删除
    Boolean deleteById(Integer id);
}
