package com.qiu.service;

import com.qiu.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/18 11:30
 * @description 订单业务逻辑
 */
public interface OrderService {
    /**
     * 通过ID查询
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    Order selectById(Integer orderId);

    /**
     * 查询所以订单列表
     *
     * @return 订单列表
     */
    List<Order> selectAll();

    /**
     * 查询用户的订单基本信息
     *
     * @param userAccount 用户账号
     * @return 订单信息
     */
    List<Map<String, Object>> selectAllOrder(String userAccount);

    /**
     * 查询订单基本信息
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    Map<String, Object> selectOrder(String orderNo);

    /**
     * 查询订单数量
     *
     * @return 订单数量
     */
    Integer selectCount();

    /**
     * 根据订单编号查询
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    Order selectByKey(String orderNo);

    /**
     * 根据订单编号查询订单ID
     *
     * @param orderNo 订单编号
     * @return 订单ID
     */
    Integer selectIdByKey(String orderNo);

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 是否添加成功
     */
    Boolean insertData(Order order);

    /**
     * 更新订单
     *
     * @param order 订单信息
     * @return 是否更新成功
     */
    Boolean updateById(Order order);

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer orderId);

    /**
     * 查询今日订单数量、今日销售总额、昨日销售总额等信息
     *
     * @return 订单统计数据
     */
    Map<String, Object> orderOverview();

    /**
     * 获取订单图表分析数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单统计数据
     */
    List<Map<String, Object>> selectChartDate(String startTime, String endTime);

    /**
     * 获取订单商品类别图表分析数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单统计数据
     */
    List<Map<String, Object>> selectProductTypeChart(String startTime, String endTime);

    /**
     * 获取某个时间段，订单总数和销售总额
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单统计数据
     */
    Map<String, Object> selectCountAndAmount(String startTime, String endTime);
}
