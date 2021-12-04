package com.qiu.dao;

import java.util.List;

public interface BaseDao<T> {
    //通过ID查询
    T selectById(Integer id);
    //查询所有
    List<T> selectAll();
    //查询记录个数
    Integer selectCount();
    //通过Key查Id
    Integer selectIdByKey(String key);
    //通过Key查询
    T selectByKey(String key);
    //判断主键是否存在
    Boolean existsWithPrimaryKey(String key);
    //查询最大Id
    Integer selectMaxId();

    //插入一条数据
    Boolean insertData(T t);

    //通过ID更新
    Boolean updateById(T t);

    //通过ID删除
    Boolean deleteById(Integer id);
}