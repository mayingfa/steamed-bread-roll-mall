package com.qiu.service;

import com.qiu.entity.Supplier;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:16
 * @description 供应商业务逻辑
 */
public interface SupplierService {
    /**
     * 通过供应商ID查询供应商信息
     *
     * @param supplierId 供应商ID
     * @return 供应商
     */
    Supplier selectById(Integer supplierId);

    /**
     * 查询所有供应商
     *
     * @return 供应商
     */
    List<Supplier> selectAll();

    /**
     * 查询所有供应商名称
     *
     * @return 供应商名称
     */
    List<String> selectAllName();

    /**
     * 查询供应商是否存在
     *
     * @param supplierId   供应商ID
     * @param supplierName 供应商名称
     * @return 供应商是否存在
     */
    Boolean existsWithSupplierName(Integer supplierId, String supplierName);


    /**
     * 查询所有可用的供应商
     *
     * @return 供应商信息
     */
    List<Supplier> selectAllUsable();

    /**
     * 添加供应商信息
     *
     * @param supplier 供应商
     * @return 是否添加成功
     */
    Boolean insertData(Supplier supplier);

    /**
     * 更新供应商信息
     *
     * @param supplier 供应商
     * @return 是否更新成功
     */
    Boolean updateById(Supplier supplier);

    /**
     * 删除供应商信息
     *
     * @param supplierId 供应商ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer supplierId);
}
