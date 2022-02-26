package com.qiu.service;

import com.qiu.entity.ReturnGoods;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 22:19
 * @description 商品退货业务逻辑
 */
public interface ReturnGoodsService {
    /**
     * 通过ID查询
     *
     * @param returnId 退货ID
     * @return 商品退货信息
     */
    ReturnGoods selectById(Integer returnId);

    /**
     * 查询所有退货信息
     *
     * @return 退货信息列表
     */
    List<ReturnGoods> selectAll();


    /**
     * 查询退货数量个数
     *
     * @return 退货数量
     */
    Integer selectCount();

    /**
     * 新增商品退货信息
     *
     * @param returnGoods 退货信息
     * @return 是否新增成功
     */
    Boolean insertData(ReturnGoods returnGoods);

    /**
     * 更新退货信息
     *
     * @param returnGoods 退货信息
     * @return 是否更新成功
     */
    Boolean updateById(ReturnGoods returnGoods);

    /**
     * 删除退货信息
     *
     * @param returnId 退货信息编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer returnId);


    /**
     * 查询全部退货订单基本信息
     *
     * @param userAccount 用户账号
     * @return 退货信息
     */
    List<Map<String, Object>> selectAllOrder(String userAccount);

    /**
     * 查询未退款的订单
     *
     * @return 未退款订单
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
