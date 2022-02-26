package com.qiu.dao;

import com.qiu.entity.ProductBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 16:51
 * @description 商品品牌
 */
public interface ProductBrandDao extends BaseDao<ProductBrand> {
    /**
     * 查询所有品牌名
     *
     * @return 品牌名
     */
    List<String> selectAllName();

    /**
     * 通过品牌名删除
     *
     * @param brandName 品牌名
     * @return 是否删除成功
     */
    Boolean deleteByName(@Param("brandName") String brandName);

    /**
     * 查询商品类别是否存在
     *
     * @param brandId   品牌编号
     * @param brandName 品牌名
     * @return 是否存在
     */
    Boolean existsWithBrandName(@Param("brandId") Integer brandId, @Param("brandName") String brandName);
}
