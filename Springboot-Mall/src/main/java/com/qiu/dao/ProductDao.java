package com.qiu.dao;

import com.qiu.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:14
 * @description 商品表
 */
public interface ProductDao extends BaseDao<Product> {
    /**
     * 根据商品分类查询
     *
     * @return 商品信息
     */
    List<Map<String, Object>> selectAllByType();

    /**
     * 根据商品名称模糊查询
     *
     * @param productName 商品名称
     * @return 商品信息
     */
    List<Product> selectAllLikeName(String productName);

    /**
     * 根据商品类型模糊查询
     *
     * @param productType 商品类别
     * @return 商品信息
     */
    List<Product> selectAllLikeType(String productType);

    /**
     * 根据商品品牌模糊查询
     *
     * @param productBrand 商品品牌
     * @return 商品信息
     */
    List<Product> selectAllLikeBrand(String productBrand);

    /**
     * 查询所有上架商品，并按开售时间排序
     *
     * @return 正在售卖的商品
     */
    List<Product> selectAllSale();

    /**
     * 查询某个商品类别下是否存在商品信息
     *
     * @param productType 商品类别
     * @return 是否存在商品
     */
    Boolean existsProductType(String productType);

    /**
     * 查询某个商品品牌下是否存在商品信息
     *
     * @param productBrand 商品品牌
     * @return 是否存在商品
     */
    Boolean existsProductBrand(String productBrand);

    /**
     * 查询商品数量、商品上架数量、商品下架数量和库存紧张数量
     *
     * @return 统计数据
     */
    Map<String, Object> productOverview();
}
