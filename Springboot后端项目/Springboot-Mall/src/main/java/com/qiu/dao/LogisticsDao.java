package com.qiu.dao;

import com.qiu.entity.Logistics;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/26 17:44
 * @description 物流表 dao层
 */
public interface LogisticsDao extends BaseDao<Logistics> {
    //根据订单编号查询
    Logistics selectOrderNo(String orderNo);
}
