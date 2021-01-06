package com.qiu.service;

import com.qiu.dao.ShoppingCartDao;
import com.qiu.entity.ShoppingCart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:46
 * @description 购物车 service 实现类
 */

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ShoppingCart selectById(Integer cartId) {
        return shoppingCartDao.selectById(cartId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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
