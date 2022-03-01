package com.qiu.service.impl;

import com.qiu.constant.UserStatusEnum;
import com.qiu.dao.UserDao;
import com.qiu.entity.Role;
import com.qiu.entity.User;
import com.qiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:57
 * @description 用户业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public User selectByKey(String key) {
        return userDao.selectByKey(key);
    }

    @Override
    public List<Role> getRoleList(Integer userId) {
        return userDao.getRoleList(userId);
    }

    @Override
    public User selectByPhone(String telephone) {
        return userDao.selectByPhone(telephone);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Integer selectCount() {
        return userDao.selectCount();
    }

    @Override
    public Boolean selectUserState(String accountNumber) {
        return userDao.selectUserState(accountNumber);
    }

    @Override
    public Boolean existsWithPrimaryKey(String existUser) {
        return userDao.existsWithPrimaryKey(existUser);
    }

    @Override
    public Boolean existsWithPrimaryPhone(String telephone) {
        return userDao.existsWithPrimaryPhone(telephone);
    }

    @Override
    public Integer selectIdByKey(String key) {
        return userDao.selectIdByKey(key);
    }

    @Override
    public Boolean insertData(User user) {
        user.setCreatTime(new Date());
        return userDao.insertData(user);
    }

    @Override
    public Boolean updateById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> queryAllByStatus(UserStatusEnum status) {
        return userDao.queryAllByStatus(status);
    }
}
