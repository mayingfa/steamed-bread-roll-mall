package com.qiu.service;

import com.qiu.entity.ProductSpecs;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 19:11
 * @description 商品规格业务逻辑
 */
public interface ProductSpecsService {
    /**
     * 查询所有商品规格
     *
     * @return 商品规格列表
     */
    List<ProductSpecs> selectAll();

    /**
     * 根据商品id查询所有商品规格名称
     *
     * @param productId 商品ID
     * @return 商品规格名称列表
     */
    List<String> selectAllByProId(Integer productId);

    /**
     * 新增商品规格
     *
     * @param productSpecs 商品规格
     * @return 是否添加成功
     */
    Boolean insertData(ProductSpecs productSpecs);

    /**
     * 更新商品规格
     *
     * @param productSpecs 商品规格
     * @return 是否更新成功
     */
    Boolean updateById(ProductSpecs productSpecs);

    /**
     * 删除商品规格
     *
     * @param productSpecs 商品规格
     * @return 是否删除成功
     */
    Boolean deleteById(ProductSpecs productSpecs);

    /**
     * 批量新增商品规格
     *
     * @param productSpecsList 商品规格列表
     * @return 是否添加成功
     */
    Boolean insertBatch(List<ProductSpecs> productSpecsList);
}
