package com.qiu.dao;

import com.qiu.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:01
 * @description 供应商 dao层
 */
public interface SupplierDao extends BaseDao<Supplier>{
    /*查询所有供应商名称*/
    List<String> selectAllName();
    //查询所有启用的供应商
    List<Supplier> selectAllUsable();
    //判断供应商是否存在
    Boolean existsWithSupplierName(@Param("supplierId") Integer supplierId,@Param("supplierName") String supplierName);
}
