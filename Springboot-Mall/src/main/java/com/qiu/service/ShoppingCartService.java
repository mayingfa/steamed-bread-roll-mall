package com.qiu.service;

import com.qiu.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/2 9:14
 * @description 购物车业务逻辑
 */
public interface ShoppingCartService {

    /**
     * 根据ID查询购物车信息
     *
     * @param cartId 购物车ID
     * @return 购物车信息
     */
    ShoppingCart selectById(Integer cartId);

    /**
     * 查询某个账户下购物车信息
     *
     * @param accountNumber 账号信息
     * @return 购物车信息
     */
    List<Map<String, Object>> selectAll(String accountNumber);

    /**
     * 新增购物车信息
     *
     * @param shoppingCart 购物车信息
     * @return 是否新增成功
     */
    Boolean insertData(ShoppingCart shoppingCart);

    /**
     * 更新购物车信息
     *
     * @param shoppingCart 购物车信息
     * @return 是否更新成功
     */
    Boolean updateById(ShoppingCart shoppingCart);

    /**
     * 删除购物车记录
     *
     * @param cartId 购物车ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer cartId);

    /**
     * 清除用户账号下的购物车记录
     *
     * @param accountNumber 用户账号
     * @return 是否删除成功
     */
    Boolean deleteByUser(String accountNumber);
}
