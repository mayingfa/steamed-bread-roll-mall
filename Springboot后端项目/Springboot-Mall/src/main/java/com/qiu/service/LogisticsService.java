package com.qiu.service;

import com.qiu.entity.Logistics;
import com.qiu.entity.ReturnGoods;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 22:19
 * @description 物流表 service层接口
 */
public interface LogisticsService {
    //通过ID查询
    Logistics selectById(Integer logisticId);
    //查询所有退货信息
    List<Logistics> selectAll();
    //根据订单编号查询
    Logistics selectOrderNo(String orderNo);
    //插入一条数据
    Boolean insertData(Logistics logistics);
    //通过ID删除
    Boolean deleteById(Integer logisticId);
}
