package com.qiu.service;

import com.qiu.entity.Specs;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:17
 * @description 商品规格 service 接口
 */
public interface SpecsService {
    //通过ID查询
    Specs selectById(Integer specsId);
    //查询所有
    List<Specs> selectAll();
    //根据类型查询所有
    List<Specs> selectAllByType(String productType);
    //插入一条数据
    Boolean insertData(Specs specs);
    //通过ID更新
    Boolean updateById(Specs specs);
    //通过ID删除
    Boolean deleteById(Integer specsId);
    //判断商品规格是否存在
    Boolean existsWithSpecsName(Integer specsId,String specsName,String productType);
}
