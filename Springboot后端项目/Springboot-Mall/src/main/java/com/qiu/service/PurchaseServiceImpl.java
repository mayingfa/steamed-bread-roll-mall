package com.qiu.service;

import com.qiu.dao.PurchaseDao;
import com.qiu.dao.ReturnReasonDao;
import com.qiu.entity.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 11:04
 * @description 采购表 service层 实体类
 */
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDao purchaseDao;
    public PurchaseServiceImpl(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Purchase selectById(Integer purchaseId) {
        return purchaseDao.selectById(purchaseId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Purchase> selectAll() {
        return purchaseDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean existsWithPrimaryKey(String purchaseNo) {
        return purchaseDao.existsWithPrimaryKey(purchaseNo);
    }

    @Override
    public Boolean insertData(Purchase purchase) {
        return purchaseDao.insertData(purchase);
    }

    @Override
    public Boolean updateById(Purchase purchase) {
        return purchaseDao.updateById(purchase);
    }

    @Override
    public Boolean deleteById(Integer purchaseId) {
        return purchaseDao.deleteById(purchaseId);
    }
}
