package com.qiu.service;

import com.qiu.entity.Vip;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/20 16:47
 * @description 会员业务逻辑
 */
public interface VipService {
    /**
     * 通过ID查询会员信息
     *
     * @param vipId 会员ID
     * @return 会员信息
     */
    Vip selectById(Integer vipId);

    /**
     * 查询所有会员信息
     *
     * @return 会员信息
     */
    List<Vip> selectAll();

    /**
     * 查询用户账户下是否开通会员
     *
     * @param accountNumber 用户账号
     * @return 是否开通会员
     */
    Boolean existsVip(String accountNumber);

    /**
     * 根据用户账户查询会员信息
     *
     * @param accountNumber 用户账户
     * @return 会员信息
     */
    Vip selectByKey(String accountNumber);

    /**
     * 新增会员信息
     *
     * @param vip 会员信息
     * @return 是否新增成功
     */
    Boolean insertData(Vip vip);

    /**
     * 更新会员信息
     *
     * @param vip 会员信息
     * @return 是否更新成功
     */
    Boolean updateById(Vip vip);

    /**
     * 删除会员信息
     *
     * @param vipId 会员编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer vipId);

}
