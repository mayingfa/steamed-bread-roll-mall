package com.qiu.service;

import com.qiu.dao.ProductSpecsDao;
import com.qiu.entity.ProductSpecs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 19:14
 * @description 商品规格关联表 service 实现类
 */

@Service
@Transactional
public class ProductSpecsServiceImpl implements ProductSpecsService {
    final ProductSpecsDao productSpecsDao;
    public ProductSpecsServiceImpl(ProductSpecsDao productSpecsDao){
        this.productSpecsDao = productSpecsDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductSpecs> selectAll() {
        return productSpecsDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> selectAllByProId(Integer productId) {
        return productSpecsDao.selectAllByProId(productId);
    }

    @Override
    public Boolean insertData(ProductSpecs productSpecs) {
        return productSpecsDao.insertData(productSpecs);
    }

    @Override
    public Boolean updateById(ProductSpecs productSpecs) {
        return productSpecsDao.updateById(productSpecs);
    }

    @Override
    public Boolean deleteById(ProductSpecs productSpecs) {
        return productSpecsDao.deleteById(productSpecs);
    }

    @Override
    public Boolean insertBatch(List<ProductSpecs> productSpecsList) {
        return productSpecsDao.insertBatch(productSpecsList);
    }
}
