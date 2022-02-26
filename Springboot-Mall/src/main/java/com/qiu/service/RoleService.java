package com.qiu.service;

import com.qiu.entity.Role;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:39
 * @description 角色业务逻辑
 */
public interface RoleService {
    /**
     * 根据ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    Role selectById(Integer id);

    /**
     * 根据角色名称查询角色信息
     *
     * @param key 角色名称
     * @return 角色信息
     */
    Role selectByKey(String key);

    /**
     * 查询所有角色信息
     *
     * @return 角色信息列表
     */
    List<Role> selectAll();

    /**
     * 查询所有可用的角色
     *
     * @return 角色信息列表
     */
    List<Role> selectAllUsable();

    /**
     * 查询角色名称是否存在
     *
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @return 角色是否存在
     */
    Boolean existsRoleName(Integer roleId, String roleName);

    /**
     * 查询记录个数
     *
     * @return 角色数量
     */
    Integer selectCount();

    /**
     * 通过角色名称查询角色ID
     *
     * @param key 角色名称
     * @return 角色ID
     */
    Integer selectIdByKey(String key);

    /**
     * 添加角色信息
     *
     * @param role 角色信息
     * @return 是否添加成功
     */
    Boolean insertData(Role role);

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 是否更新成功
     */
    Boolean updateById(Role role);

    /**
     * 删除角色信息
     *
     * @param id 角色ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer id);
}
