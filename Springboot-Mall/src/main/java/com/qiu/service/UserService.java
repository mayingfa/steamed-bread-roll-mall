package com.qiu.service;

import com.qiu.entity.Role;
import com.qiu.entity.User;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:55
 * @description 用户业务逻辑
 */
public interface UserService {
    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User selectById(Integer id);

    /**
     * 根据用户账号查询用户
     *
     * @param key 用户账号
     * @return 用户信息
     */
    User selectByKey(String key);

    /**
     * 获取用户对应角色
     *
     * @param userId 用ID
     * @return 角色列表
     */
    List<Role> getRoleList(Integer userId);

    /**
     * 通过手机号查询用户
     *
     * @param telephone 手机号
     * @return 手机号
     */
    User selectByPhone(String telephone);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<User> selectAll();

    /**
     * 查询用户数量
     *
     * @return 用户数量
     */
    Integer selectCount();

    /**
     * 查询用户状态
     *
     * @param accountNumber 用户账号
     * @return 是否可用
     */
    Boolean selectUserState(String accountNumber);

    /**
     * 查询账号是否存在
     *
     * @param existUser 用户账号
     * @return 是否存在
     */
    Boolean existsWithPrimaryKey(String existUser);

    /**
     * 查询手机号是否被绑定
     *
     * @param telephone 手机号
     * @return 是否被使用
     */
    Boolean existsWithPrimaryPhone(String telephone);

    /**
     * 通过账号查询用户ID
     *
     * @param key 用户账号
     * @return 用户ID
     */
    Integer selectIdByKey(String key);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 是否添加成功
     */
    Boolean insertData(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 是否更新成功
     */
    Boolean updateById(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer id);
}
