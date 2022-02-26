package com.qiu.service;

import com.qiu.entity.Logistics;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 22:19
 * @description 物流表
 */
public interface LogisticsService {
    /**
     * 通过编号查询
     * @param logisticId 物流编号
     * @return  物流信息
     */
    Logistics selectById(Integer logisticId);

    /**
     * 查询所有物流信息
     * @return 物流信息列表
     */
    List<Logistics> selectAll();

    /**
     * 根据订单编号查询物流信息
     * @param orderNo   订单编号
     * @return  物流信息
     */
    Logistics selectOrderNo(String orderNo);

    /**
     * 新增物流信息
     * @param logistics 物流信息
     * @return 是否添加成功
     */
    Boolean insertData(Logistics logistics);

    /**
     * 根据物流编号删除信息
     * @param logisticId 物流编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer logisticId);
}
