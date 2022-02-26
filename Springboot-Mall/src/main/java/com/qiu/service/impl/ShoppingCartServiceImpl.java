package com.qiu.service.impl;

import com.qiu.dao.ShoppingCartDao;
import com.qiu.entity.ShoppingCart;
import com.qiu.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:46
 * @description 购物车业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart selectById(Integer cartId) {
        return shoppingCartDao.selectById(cartId);
    }

    @Override
    public List<Map<String, Object>> selectAll(String accountNumber) {
        return shoppingCartDao.selectAll(accountNumber);
    }

    @Override
    public Boolean insertData(ShoppingCart shoppingCart) {
        return shoppingCartDao.insertData(shoppingCart);
    }

    @Override
    public Boolean updateById(ShoppingCart shoppingCart) {
        return shoppingCartDao.updateById(shoppingCart);
    }

    @Override
    public Boolean deleteById(Integer cartId) {
        return shoppingCartDao.deleteById(cartId);
    }

    @Override
    public Boolean deleteByUser(String accountNumber) {
        return shoppingCartDao.deleteByUser(accountNumber);
    }
}
