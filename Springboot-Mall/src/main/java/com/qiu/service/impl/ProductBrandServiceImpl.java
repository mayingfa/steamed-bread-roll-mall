package com.qiu.service.impl;

import com.qiu.dao.ProductBrandDao;
import com.qiu.entity.ProductBrand;
import com.qiu.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 17:02
 * @description 商品品牌业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandDao productBrandDao;

    @Override
    public ProductBrand selectById(Integer brandId) {
        return productBrandDao.selectById(brandId);
    }

    @Override
    public List<ProductBrand> selectAll() {
        return productBrandDao.selectAll();
    }


    @Override
    public List<String> selectAllName() {
        return productBrandDao.selectAllName();
    }

    @Override
    public Boolean existsWithBrandName(Integer brandId, String brandName) {
        return productBrandDao.existsWithBrandName(brandId, brandName);
    }

    @Override
    public Boolean insertData(ProductBrand productBrand) {
        return productBrandDao.insertData(productBrand);
    }

    @Override
    public Boolean updateById(ProductBrand productBrand) {
        return productBrandDao.updateById(productBrand);
    }

    @Override
    public Boolean deleteById(Integer brandId) {
        return productBrandDao.deleteById(brandId);
    }

    @Override
    public Boolean deleteByName(String brandName) {
        return productBrandDao.deleteByName(brandName);
    }
}
