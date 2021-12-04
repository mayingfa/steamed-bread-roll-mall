package com.qiu.dao;

import com.qiu.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:14
 * @description 商品表 dao层接口
 */
public interface ProductDao extends BaseDao<Product> {
    //根据商品分类查询
    List<Map<String,Object>> selectAllByType();
    //根据商品名称模糊查询
    List<Product> selectAllLikeName(String keyWord);
    //根据商品类型模糊查询
    List<Product> selectAllLikeType(String keyWord);
    //根据商品品牌模糊查询
    List<Product> selectAllLikeBrand(String keyWord);
    //查询所有上架商品，并按开售时间排序
    List<Product> selectAllSale();
    //判断商品类别是否存在
    Boolean existsProductType(String productType);
    //判断商品类别是否存在
    Boolean existsProductBrand(String productBrand);
    //查询商品数量、商品上架数量、商品下架数量和库存紧张数量
    Map<String,Object> productOverview();
}
