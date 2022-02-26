package com.qiu.service;

import com.qiu.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:47
 * @description 商品信息业务逻辑
 */
public interface ProductService {
    /**
     * 根据商品ID查询商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    Product selectById(Integer productId);

    /**
     * 根据商品编号查询商品信息
     *
     * @param productNo 商品编号
     * @return 商品信息
     */
    Product selectByKey(String productNo);

    /**
     * 根据商品编号查询商品ID
     *
     * @param productNo 商品编号
     * @return 商品编号
     */
    Integer selectIdByKey(String productNo);

    /**
     * 查询商品数量
     *
     * @return 商品数量
     */
    Integer selectCount();

    /**
     * 查询商品编号是否存在
     *
     * @param productNo 商品编号
     * @return 商品是否存在
     */
    Boolean existsWithPrimaryKey(String productNo);

    /**
     * 查询商品类别下是否存在商品
     *
     * @param productType 商品类别
     * @return 是否存在商品
     */
    Boolean existsProductType(String productType);

    /**
     * 查询商品品牌下是否存在商品
     *
     * @param productBrand 商品品牌
     * @return 是否存在商品
     */
    Boolean existsProductBrand(String productBrand);

    /**
     * 查询所有商品
     *
     * @return 商品信息列表
     */
    List<Product> selectAll();

    /**
     * 查询所有上架商品，并按开售时间排序
     *
     * @return 商品信息
     */
    List<Product> selectAllSale();

    /**
     * 根据商品分类查询,并通过开售时间优先排序
     *
     * @return 商品信息
     */
    List<Map<String, Object>> selectAllByType();

    /**
     * 新增商品信息
     *
     * @param product 商品信息
     * @return 是否新增成功
     */
    Boolean insertData(Product product);

    /**
     * 更新商品信息
     *
     * @param product 商品信息
     * @return 是否更新成功
     */
    Boolean updateById(Product product);

    /**
     * 删除商品
     *
     * @param productId 商品ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer productId);

    /**
     * 根据商品名称模糊查询
     *
     * @param keyWord 商品名称
     * @return 商品列表
     */
    List<Product> selectAllLikeName(String keyWord);

    /**
     * 根据商品类型模糊查询
     *
     * @param keyWord 商品类型
     * @return 商品列表
     */
    List<Product> selectAllLikeType(String keyWord);

    /**
     * 根据商品品牌模糊查询
     *
     * @param keyWord 商品品牌
     * @return 商品列表
     */
    List<Product> selectAllLikeBrand(String keyWord);

    /**
     * 查询商品数量、商品上架数量、商品下架数量和库存紧张数量
     *
     * @return 商品统计数据
     */
    Map<String, Object> productOverview();
}
