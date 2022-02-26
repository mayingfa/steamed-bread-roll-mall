package com.qiu.service;

import com.qiu.entity.Specs;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:17
 * @description 商品规格业务逻辑
 */
public interface SpecsService {
    /**
     * 根据ID查询商品规格
     *
     * @param specsId 商品规格ID
     * @return 商品规格
     */
    Specs selectById(Integer specsId);

    /**
     * 查询所有商品规格
     *
     * @return 商品规格
     */
    List<Specs> selectAll();

    /**
     * 根据商品类型查询所有商品规格
     *
     * @param productType 商品类别
     * @return 商品规格
     */
    List<Specs> selectAllByType(String productType);

    /**
     * 新增商品规格
     *
     * @param specs 商品规格
     * @return 是否新增成功
     */
    Boolean insertData(Specs specs);

    /**
     * 更新商品规格
     *
     * @param specs 商品规格
     * @return 是否更新成功
     */
    Boolean updateById(Specs specs);

    /**
     * 通过ID删除
     *
     * @param specsId 商品规格ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer specsId);

    /**
     * 查询商品规格是否存在
     *
     * @param specsId     商品规格编号
     * @param specsName   商品规格名称
     * @param productType 商品类别
     * @return 是否存在
     */
    Boolean existsWithSpecsName(Integer specsId, String specsName, String productType);
}
