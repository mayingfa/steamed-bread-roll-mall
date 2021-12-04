package com.qiu.service;

import com.qiu.dao.ProductTypeDao;
import com.qiu.entity.ProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:46
 * @description 商品分类 service 实现类
 */

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeDao productTypeDao;
    public ProductTypeServiceImpl(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ProductType selectById(Integer typeId) {
        return productTypeDao.selectById(typeId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductType> selectAll() {
        return productTypeDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean existsWithTypeName(Integer typeId, String typeName) {
        return productTypeDao.existsWithTypeName(typeId,typeName);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> selectAllName() {
        return productTypeDao.selectAllName();
    }

    @Override
    public Boolean insertData(ProductType productType) {
        return productTypeDao.insertData(productType);
    }

    @Override
    public Boolean updateById(ProductType productType) {
        return productTypeDao.updateById(productType);
    }

    @Override
    public Boolean deleteById(Integer typeId) {
        return productTypeDao.deleteById(typeId);
    }

    @Override
    public Boolean deleteByName(String typeName) {
        return productTypeDao.deleteByName(typeName);
    }
}
