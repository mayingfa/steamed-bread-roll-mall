package com.qiu.dao;
import com.qiu.entity.UserRole;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:22
 * @description userRole 的 dao层接口
 */
public interface UserRoleDao extends BaseDao<UserRole> {
    List<UserRole> selectByUserId(Integer userId);
    //通过ID删除
    Boolean deleteById(Integer userId);
    //通过用户id查询角色信息
    List<Map<String,Object>>selectRoleByUserId(Integer userId);
    //判断角色是否存在
    Boolean existsRole(Integer roleId);
}
