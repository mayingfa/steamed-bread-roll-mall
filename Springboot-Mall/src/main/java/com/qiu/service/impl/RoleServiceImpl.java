package com.qiu.service.impl;

import com.qiu.dao.RoleDao;
import com.qiu.entity.Role;
import com.qiu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:40
 * @description 角色信息业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role selectById(Integer id) {
        return roleDao.selectById(id);
    }

    @Override
    public Role selectByKey(String key) {
        return roleDao.selectByKey(key);
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public List<Role> selectAllUsable() {
        return roleDao.selectAllUsable();
    }

    @Override
    public Boolean existsRoleName(Integer roleId, String roleName) {
        return roleDao.existsRoleName(roleId, roleName);
    }

    @Override
    public Integer selectCount() {
        return roleDao.selectCount();
    }

    @Override
    public Integer selectIdByKey(String key) {
        return roleDao.selectIdByKey(key);
    }

    @Override
    public Boolean insertData(Role role) {
        return roleDao.insertData(role);
    }

    @Override
    public Boolean updateById(Role role) {
        return roleDao.updateById(role);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return roleDao.deleteById(id);
    }
}
