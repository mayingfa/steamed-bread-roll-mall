package com.qiu.dao;

import com.qiu.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/2 9:19
 * @description 购物车表
 */
public interface ShoppingCartDao extends BaseDao<ShoppingCart> {
    /**
     * 查询用户购物车信息
     *
     * @param accountNumber 用户账号
     * @return 购物车信息
     */
    List<Map<String, Object>> selectAll(String accountNumber);

    /**
     * 通过用户账号删除
     *
     * @param accountNumber 用户账号
     * @return 是否删除成功
     */
    Boolean deleteByUser(String accountNumber);
}
