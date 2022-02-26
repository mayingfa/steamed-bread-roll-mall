package com.qiu.dao;

import com.qiu.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:01
 * @description 供应商
 */
public interface SupplierDao extends BaseDao<Supplier> {
    /**
     * 查询所有供应商名称
     *
     * @return 供应商列表
     */
    List<String> selectAllName();

    /**
     * 查询所有启用的供应商
     *
     * @return 供应商列表
     */
    List<Supplier> selectAllUsable();

    /**
     * 查询供应商是否存在
     *
     * @param supplierId   供应商编号
     * @param supplierName 供应商名称
     * @return 是否存在
     */
    Boolean existsWithSupplierName(@Param("supplierId") Integer supplierId,
                                   @Param("supplierName") String supplierName);
}
