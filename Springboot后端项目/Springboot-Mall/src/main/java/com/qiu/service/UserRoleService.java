package com.qiu.service;

import com.qiu.entity.UserRole;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:40
 * @description service接口，用户的权限
 */
public interface UserRoleService {
    //通过id查询
    List<UserRole> selectByUserId(Integer userId);
    //查询所有
    List<UserRole> selectAll();
    //插入一条数据
    Boolean insertData(UserRole userRole);
    //通过ID删除
    Boolean deleteById(Integer userId);
    //通过用户id查询角色信息
    List<Map<String,Object>>selectRoleByUserId(Integer userId);
    //判断角色是否存在
    Boolean existsRole(Integer roleId);
}
