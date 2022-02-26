package com.qiu.service.impl;

import com.qiu.dao.LogisticsDao;
import com.qiu.entity.Logistics;
import com.qiu.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/26 17:50
 * @description 物流业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogisticsServiceImpl implements LogisticsService {
    @Autowired
    private LogisticsDao logisticsDao;

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
