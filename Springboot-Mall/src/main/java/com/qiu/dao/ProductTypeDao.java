package com.qiu.dao;

import com.qiu.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:33
 * @description 描述
 */
public interface ProductTypeDao extends BaseDao<ProductType> {
    /*查询所有分类名*/
    List<String> selectAllName();
    //通过分类名删除
    Boolean deleteByName(String typeName);
    //判断商品类别是否存在
    Boolean existsWithTypeName(@Param("typeId") Integer typeId,@Param("typeName") String typeName);
}
