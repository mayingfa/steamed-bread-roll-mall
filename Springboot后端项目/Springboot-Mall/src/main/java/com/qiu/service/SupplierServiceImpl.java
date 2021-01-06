package com.qiu.service;

import com.qiu.dao.SupplierDao;
import com.qiu.entity.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:17
 * @description 供应商 service 实现类
 */

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    final SupplierDao supplierDao;
    public SupplierServiceImpl(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }


    @Override
    public Supplier selectById(Integer supplierId) {
        return supplierDao.selectById(supplierId);
    }

    @Override
    public List<Supplier> selectAll() {
        return supplierDao.selectAll();
    }

    @Override
    public List<String> selectAllName() {
        return supplierDao.selectAllName();
    }

    @Override
    public Boolean existsWithSupplierName(Integer supplierId, String supplierName) {
        return supplierDao.existsWithSupplierName(supplierId,supplierName);
    }

    @Override
    public Boolean insertData(Supplier supplier) {
        return supplierDao.insertData(supplier);
    }

    @Override
    public List<Supplier> selectAllUsable() {
        return supplierDao.selectAllUsable();
    }

    @Override
    public Boolean updateById(Supplier supplier) {
        return supplierDao.updateById(supplier);
    }

    @Override
    public Boolean deleteById(Integer supplierId) {
        return supplierDao.deleteById(supplierId);
    }
}
