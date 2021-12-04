package com.qiu.service;

import com.qiu.dao.OrderDao;
import com.qiu.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/18 11:32
 * @description 订单表 service 实现类
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    final OrderDao orderDao;
    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao=orderDao;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Order selectById(Integer orderId) {
        return orderDao.selectById(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Order> selectAll() {
        return orderDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Map<String, Object>> selectAllOrder(String userAccount) {
        return orderDao.selectAllOrder(userAccount);
    }

    @Override
    public Map<String, Object> selectOrder(String orderNo) {
        return orderDao.selectOrder(orderNo);
    }

    @Override
    public Integer selectCount() {
        return orderDao.selectCount();
    }

    @Override
    public Order selectByKey(String orderNo) {
        return orderDao.selectByKey(orderNo);
    }

    @Override
    public Integer selectIdByKey(String orderNo) {
        return orderDao.selectIdByKey(orderNo);
    }

    @Override
    public Boolean insertData(Order order) {
        order.setOrderTime(new Date());
        return orderDao.insertData(order);
    }

    @Override
    public Boolean updateById(Order order) {
        return orderDao.updateById(order);
    }

    @Override
    public Boolean deleteById(Integer orderId) {
        return orderDao.deleteById(orderId);
    }

    @Override
    public Map<String, Object> orderOverview() {
        return orderDao.orderOverview();
    }

    @Override
    public List<Map<String, Object>> selectChartDATE(String startTime, String endTime) {
        return orderDao.selectChartDATE(startTime,endTime);
    }

    @Override
    public List<Map<String, Object>> selectProductTypeChart(String startTime, String endTime) {
        return orderDao.selectProductTypeChart(startTime,endTime);
    }

    @Override
    public Map<String, Object> selectCountAndAmount(String startTime, String endTime) {
        return orderDao.selectCountAndAmount(startTime,endTime);
    }
}
