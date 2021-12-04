package com.qiu.dao;

import com.qiu.entity.Vip;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/20 16:34
 * @description vip表 dao 层
 */
public interface VipDao extends BaseDao<Vip> {
    //判断主键是否存在
    Boolean existsVip(String accountNumber);
}
