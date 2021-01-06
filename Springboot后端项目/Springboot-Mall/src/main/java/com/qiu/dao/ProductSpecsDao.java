package com.qiu.dao;

import com.qiu.entity.ProductSpecs;
import com.qiu.entity.Specs;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:49
 * @description 商品与商品规格表 dao层接口
 */
public interface ProductSpecsDao extends BaseDao<ProductSpecs> {
    //根据商品id查询所有商品规格名称
    List<String> selectAllByProId(Integer productId);
    //通过ID删除
    Boolean deleteById(ProductSpecs productSpecs);
    //批量添加
    Boolean insertBatch(List<ProductSpecs> productSpecsList);
}
