package com.qiu.service;

import com.qiu.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:40
 * @description 用户角色业务逻辑
 */
public interface UserRoleService {
    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return 用户角色信息
     */
    List<UserRole> selectByUserId(Integer userId);

    /**
     * 查询所有用户角色
     *
     * @return 用户角色
     */
    List<UserRole> selectAll();

    /**
     * 新增用户角色信息
     *
     * @param userRole 用户角色
     * @return 是否新增成功
     */
    Boolean insertData(UserRole userRole);

    /**
     * 删除用户角色信息
     *
     * @param userId 用户ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer userId);

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    List<Map<String, Object>> selectRoleByUserId(Integer userId);

    /**
     * 查询角色是否存在
     *
     * @param roleId 角色ID
     * @return 是否存在
     */
    Boolean existsRole(Integer roleId);
}
