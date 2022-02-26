package com.qiu.dao;

import com.qiu.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:33
 * @description 商品类别表
 */
public interface ProductTypeDao extends BaseDao<ProductType> {
    /**
     * 查询所有分类名
     *
     * @return 商品类别名称
     */
    List<String> selectAllName();

    /**
     * 通过分类名删除
     *
     * @param typeName 商品类别名称
     * @return 是否删除成功
     */
    Boolean deleteByName(String typeName);

    /**
     * 查询商品类别是否存在
     *
     * @param typeId   类别编号
     * @param typeName 类别名称
     * @return 是否存在
     */
    Boolean existsWithTypeName(@Param("typeId") Integer typeId, @Param("typeName") String typeName);
}
