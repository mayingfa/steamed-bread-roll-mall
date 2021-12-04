package com.qiu.service;

import com.qiu.entity.Purchase;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 11:01
 * @description 采购表 service层接口
 */
public interface PurchaseService {
    //通过ID查询
    Purchase selectById(Integer purchaseId);
    //查询所有供应商
    List<Purchase> selectAll();
    //判断供应商是否存在
    Boolean existsWithPrimaryKey(String purchaseNo);
    //插入一条数据
    Boolean insertData(Purchase purchase);

    //通过ID更新
    Boolean updateById(Purchase purchase);

    //通过ID删除
    Boolean deleteById(Integer purchaseId);
}
