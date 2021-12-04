package com.qiu.service;

import com.qiu.dao.RoleDao;
import com.qiu.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:40
 * @description role 的 service层实现类
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    final RoleDao roleDao;
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role selectById(Integer id) {
        return roleDao.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role selectByKey(String key) {
        return roleDao.selectByKey(key);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public List<Role> selectAllUsable() {
        return roleDao.selectAllUsable();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean existsRoleName(Integer roleId, String roleName) {
        return roleDao.existsRoleName(roleId,roleName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer selectCount() {
        return roleDao.selectCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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
