package com.qiu.service;

import com.qiu.entity.ProductBrand;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 16:59
 * @description 商品品牌业务逻辑
 */
public interface ProductBrandService {
    /**
     * 通过ID查询
     *
     * @param brandId 品牌ID
     * @return 商品品牌
     */
    ProductBrand selectById(Integer brandId);

    /**
     * 查询所以品牌
     *
     * @return 商品品牌列表
     */
    List<ProductBrand> selectAll();

    /**
     * 查询所有品牌名
     *
     * @return 品牌名
     */
    List<String> selectAllName();

    /**
     * 查询商品类别是否存在
     *
     * @param brandId   品牌编号
     * @param brandName 品牌名称
     * @return 是否存在
     */
    Boolean existsWithBrandName(Integer brandId, String brandName);

    /**
     * 新增商品品牌
     *
     * @param productBrand 商品品牌
     * @return 是否添加成功
     */
    Boolean insertData(ProductBrand productBrand);

    /**
     * 更新商品品牌
     *
     * @param productBrand 商品品牌
     * @return 是否更新成功
     */
    Boolean updateById(ProductBrand productBrand);

    /**
     * 删除商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer brandId);

    /**
     * 通过品牌名删除
     *
     * @param brandName 商品品牌名
     * @return 是否删除成功
     */
    Boolean deleteByName(String brandName);
}
