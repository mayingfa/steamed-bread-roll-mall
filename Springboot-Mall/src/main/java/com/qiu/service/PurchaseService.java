package com.qiu.service;

import com.qiu.entity.Purchase;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 11:01
 * @description 采购表业务逻辑
 */
public interface PurchaseService {

    /**
     * 根据采购ID查询采购信息
     *
     * @param purchaseId 采购ID
     * @return 采购信息
     */
    Purchase selectById(Integer purchaseId);

    /**
     * 查询所有采购信息
     *
     * @return 采购信息
     */
    List<Purchase> selectAll();

    /**
     * 查询采购信息是否存在
     *
     * @param purchaseNo 采购编号
     * @return 是否存在
     */
    Boolean existsWithPrimaryKey(String purchaseNo);

    /**
     * 新增采购记录
     *
     * @param purchase 采购信息
     * @return 是否新增成功
     */
    Boolean insertData(Purchase purchase);

    /**
     * 更新采购记录
     *
     * @param purchase 采购信息
     * @return 是否更新成功
     */
    Boolean updateById(Purchase purchase);

    /**
     * 删除采购记录
     *
     * @param purchaseId 采购ID
     * @return 是否删除成功
     */
    Boolean deleteById(Integer purchaseId);
}
