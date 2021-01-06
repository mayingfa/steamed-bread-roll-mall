package com.qiu.dao;
import com.qiu.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/2 9:19
 * @description 购物车表
 */
public interface ShoppingCartDao extends BaseDao<ShoppingCart> {
    //查询所有
    List<Map<String,Object>> selectAll(String accountNumber);
    //通过用户帐号删除
    Boolean deleteByUser(String accountNumber);
}
