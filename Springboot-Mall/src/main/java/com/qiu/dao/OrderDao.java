package com.qiu.dao;

import com.qiu.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/18 10:20
 * @description 订单表 dao层
 */
public interface OrderDao extends BaseDao<Order>{
    /*查询全部订单基本信息*/
    List<Map<String,Object>> selectAllOrder(String userAccount);
    /*查询订单基本信息*/
    Map<String, Object> selectOrder(String orderNo);
    //查询今日订单数量、今日销售总额、昨日销售总额等信息
    Map<String,Object> orderOverview();
    //获取订单图表分析数据
    List<Map<String,Object>>selectChartDATE(@Param("startTime")String startTime, @Param("endTime")String endTime);
    //获取订单商品类别图表分析数据
    List<Map<String,Object>>selectProductTypeChart(@Param("startTime")String startTime, @Param("endTime")String endTime);
    //获取某个时间段，订单总数和销售总额
    Map<String,Object>selectCountAndAmount(@Param("startTime")String startTime, @Param("endTime")String endTime);
}
