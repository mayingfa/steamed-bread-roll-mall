package com.qiu.dao;

import com.qiu.entity.Specs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:02
 * @description 商品规格表
 */
public interface SpecsDao extends BaseDao<Specs> {
    /**
     * 根据类型查询所有
     *
     * @param productType 商品类别
     * @return 规格列表
     */
    List<Specs> selectAllByType(String productType);

    /**
     * 查询商品规格是否存在
     *
     * @param specsId     规格编号
     * @param specsName   规格名称
     * @param productType 商品类别
     * @return 是否存在
     */
    Boolean existsWithSpecsName(@Param("specsId") Integer specsId, @Param("specsName") String specsName, @Param("productType") String productType);
}
