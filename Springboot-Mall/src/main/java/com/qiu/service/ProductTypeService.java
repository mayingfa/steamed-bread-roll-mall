package com.qiu.service;

import com.qiu.entity.ProductType;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:43
 * @description 商品分类业务逻辑
 */
public interface ProductTypeService {
    /**
     * 根据商品分类ID查询
     *
     * @param typeId 商品类别ID
     * @return 商品类别信息
     */
    ProductType selectById(Integer typeId);

    /**
     * 查询所有商品类别
     *
     * @return 商品类别列表
     */
    List<ProductType> selectAll();

    /**
     * 查询商品类别是否存在
     *
     * @param typeId   商品类别ID
     * @param typeName 商品类别名称
     * @return 是否存在
     */
    Boolean existsWithTypeName(Integer typeId, String typeName);

    /**
     * 查询所有商品类别名称
     *
     * @return 商品类别名称
     */
    List<String> selectAllName();

    /**
     * 新增商品类别
     *
     * @param productType 商品类别
     * @return 是否添加成功
     */
    Boolean insertData(ProductType productType);

    /**
     * 更新商品类别
     *
     * @param productType 商品类别
     * @return 是否更新成功
     */
    Boolean updateById(ProductType productType);

    /**
     * 删除商品类别
     *
     * @param typeId 商品类别ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer typeId);

    /**
     * 通过分类名删除
     *
     * @param typeName 商品类别名称
     * @return 是否删除成功
     */
    Boolean deleteByName(String typeName);
}
