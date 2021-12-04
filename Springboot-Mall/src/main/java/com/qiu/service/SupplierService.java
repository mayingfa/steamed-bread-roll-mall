package com.qiu.service;

import com.qiu.entity.ReturnReason;
import com.qiu.entity.Supplier;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:16
 * @description 供应商 service 接口
 */
public interface SupplierService {
    //通过ID查询
    Supplier selectById(Integer supplierId);
    //查询所有供应商
    List<Supplier> selectAll();
    /*查询所有供应商名称*/
    List<String> selectAllName();
    //判断供应商是否存在
    Boolean existsWithSupplierName(Integer supplierId,String supplierName);
    //插入一条数据
    Boolean insertData(Supplier supplier);
    //查询所有启用的供应商
    List<Supplier> selectAllUsable();
    //通过ID更新
    Boolean updateById(Supplier supplier);
    //通过ID删除
    Boolean deleteById(Integer supplierId);
}
