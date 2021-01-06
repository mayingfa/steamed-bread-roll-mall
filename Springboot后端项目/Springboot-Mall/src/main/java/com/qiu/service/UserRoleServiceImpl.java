package com.qiu.service;

import com.qiu.dao.UserRoleDao;
import com.qiu.entity.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:49
 * @description userRole 实现类
 */

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    final UserRoleDao userRoleDao;
    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserRole> selectByUserId(Integer userId) {
        return userRoleDao.selectByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserRole> selectAll() {
        return userRoleDao.selectAll();
    }

    @Override
    public Boolean insertData(UserRole userRole) {
        return userRoleDao.insertData(userRole);
    }

    @Override
    public Boolean deleteById(Integer userId) {
        return userRoleDao.deleteById(userId);
    }

    @Override
    public List<Map<String, Object>> selectRoleByUserId(Integer userId) {
        return userRoleDao.selectRoleByUserId(userId);
    }

    @Override
    public Boolean existsRole(Integer roleId) {
        return userRoleDao.existsRole(roleId);
    }
}
