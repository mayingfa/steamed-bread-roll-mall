package com.qiu.dao;

import java.util.List;

/**
 * @author Captain
 * @description 基础Mapper
 */
public interface BaseDao<T> {
    /**
     * 通过ID查询
     *
     * @param id 唯一编号
     * @return T
     */
    T selectById(Integer id);

    /**
     * 查询所有
     *
     * @return 实体列表
     */
    List<T> selectAll();

    /**
     * 查询记录个数
     *
     * @return 记录个数
     */
    Integer selectCount();

    /**
     * 通过Key查询ID
     *
     * @param key 关键字
     * @return 唯一编号
     */
    Integer selectIdByKey(String key);

    /**
     * 通过Key查询
     *
     * @param key 关键字
     * @return T
     */
    T selectByKey(String key);

    /**
     * 判断主键是否存在
     *
     * @param key 关键字
     * @return 是否存在
     */
    Boolean existsWithPrimaryKey(String key);

    /**
     * 新增数据
     *
     * @param t 实体
     * @return 是否添加成功
     */
    Boolean insertData(T t);

    /**
     * 通过ID更新
     *
     * @param t 实体
     * @return 是否更新成功
     */
    Boolean updateById(T t);

    /**
     * 通过ID删除
     *
     * @param id 唯一编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer id);
}