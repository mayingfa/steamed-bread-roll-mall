package com.qiu.dao;

import com.qiu.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:22
 * @description userRole
 */
public interface UserRoleDao extends BaseDao<UserRole> {
    /**
     * 根据用户编号查询用户角色信息
     *
     * @param userId 用户编号
     * @return 用户角色信息
     */
    List<UserRole> selectByUserId(Integer userId);

    /**
     * 根据用户编号删除用户角色信息
     *
     * @param userId 用户编号
     * @return 是否删除成功
     */
    @Override
    Boolean deleteById(Integer userId);

    /**
     * 根据用户编号查询角色信息
     *
     * @param userId 用户编号
     * @return 角色信息
     */
    List<Map<String, Object>> selectRoleByUserId(Integer userId);

    /**
     * 查询角色是否存在
     *
     * @param roleId 角色编号
     * @return 角色是否存在
     */
    Boolean existsRole(Integer roleId);
}
