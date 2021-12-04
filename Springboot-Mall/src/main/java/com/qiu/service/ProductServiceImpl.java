package com.qiu.service;

import com.qiu.dao.ProductDao;
import com.qiu.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:55
 * @description 商品信息 service 实现类
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService  {

    private final ProductDao productDao;
    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public Product selectById(Integer productId) {
        return productDao.selectById(productId);
    }

    @Override
    public Product selectByKey(String productNo) {
        return productDao.selectByKey(productNo);
    }

    @Override
    public Integer selectIdByKey(String productNo) {
        return productDao.selectIdByKey(productNo);
    }

    @Override
    public Integer selectCount() {
        return productDao.selectCount();
    }

    @Override
    public Boolean existsWithPrimaryKey(String productNo) {
        return productDao.existsWithPrimaryKey(productNo);
    }

    @Override
    public Boolean existsProductType(String productType) {
        return productDao.existsProductType(productType);
    }

    @Override
    public Boolean existsProductBrand(String productBrand) {
        return productDao.existsProductBrand(productBrand);
    }

    @Override
    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    @Override
    public List<Product> selectAllSale() {
        return productDao.selectAllSale();
    }

    @Override
    public List<Map<String,Object>> selectAllByType() {
        return productDao.selectAllByType();
    }

    @Override
    public Boolean insertData(Product product) {
        product.setSaleTime(new Date());
        return productDao.insertData(product);
    }

    @Override
    public Boolean updateById(Product product) {
        return productDao.updateById(product);
    }

    @Override
    public Boolean deleteById(Integer productId) {
        return productDao.deleteById(productId);
    }

    @Override
    public List<Product> selectAllLikeName(String keyWord) {
        return productDao.selectAllLikeName(keyWord);
    }

    @Override
    public List<Product> selectAllLikeType(String keyWord) {
        return productDao.selectAllLikeType(keyWord);
    }

    @Override
    public List<Product> selectAllLikeBrand(String keyWord) {
        return productDao.selectAllLikeBrand(keyWord);
    }

    @Override
    public Map<String, Object> productOverview() {
        return productDao.productOverview();
    }
}
