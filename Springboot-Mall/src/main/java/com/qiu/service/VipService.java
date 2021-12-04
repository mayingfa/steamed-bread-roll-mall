package com.qiu.service;

import com.qiu.entity.Vip;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/20 16:47
 * @description vip service 层
 */
public interface VipService {
    //通过ID查询
    Vip selectById(Integer vipId);
    //查询所有
    List<Vip> selectAll();
    //判断主键是否存在
    Boolean existsVip(String accountNumber);
    //根据用户账户查询
    Vip selectByKey(String accountNumber);

    //插入一条数据
    Boolean insertData(Vip vip);

    //通过ID更新
    Boolean updateById(Vip vip);

    //通过ID删除
    Boolean deleteById(Integer vipId);

}
