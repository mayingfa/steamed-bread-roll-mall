package com.qiu.dao;


import com.qiu.entity.ReturnGoods;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 22:11
 * @description 商品退货表
 */
public interface ReturnGoodsDao extends BaseDao<ReturnGoods> {
    /**
     * 查询用户退货商品信息
     *
     * @param userNumber 用户账号
     * @return 退货信息
     */
    List<ReturnGoods> selectAllByUser(String userNumber);

    /**
     * 查询全部订单基本信息
     *
     * @param userAccount 用户账号
     * @return 退货信息
     */
    List<Map<String, Object>> selectAllOrder(String userAccount);

    /**
     * 查询未退款的订单
     *
     * @return 退货信息
     */
    Map<String, Object> returnGoodsOverview();

    /**
     * 根据订单编号查询
     *
     * @param orderNo 订单编号
     * @return 退货信息
     */
    Map<String, Object> selectOrderNo(String orderNo);
}
