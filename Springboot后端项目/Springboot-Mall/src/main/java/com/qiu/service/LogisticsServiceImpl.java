package com.qiu.service;

import com.qiu.dao.LogisticsDao;
import com.qiu.entity.Logistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/26 17:50
 * @description 物流表 service层实现类
 */
@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {
    final LogisticsDao logisticsDao;
    LogisticsServiceImpl(LogisticsDao logisticsDao){
        this.logisticsDao = logisticsDao;
    }

    @Override
    public Logistics selectById(Integer logisticId) {
        return logisticsDao.selectById(logisticId);
    }

    @Override
    public List<Logistics> selectAll() {
        return logisticsDao.selectAll();
    }

    @Override
    public Logistics selectOrderNo(String orderNo) {
        return logisticsDao.selectOrderNo(orderNo);
    }

    @Override
    public Boolean insertData(Logistics logistics) {
        return logisticsDao.insertData(logistics);
    }

    @Override
    public Boolean deleteById(Integer logisticId) {
        return logisticsDao.deleteById(logisticId);
    }
}
