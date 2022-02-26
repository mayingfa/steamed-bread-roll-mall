package com.qiu.service;

import com.qiu.entity.Banner;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:43
 * @description 商品轮播图
 */
public interface BannerService {
    /**
     * 通过编号查询
     *
     * @param bannerId 轮播图编号
     * @return 轮播图信息
     */
    Banner selectById(Integer bannerId);

    /**
     * 查询所有
     *
     * @return 轮播图列表
     */
    List<Banner> selectAll();

    /**
     * 新增轮播图
     *
     * @param banner 轮播图信息
     * @return 是否添加成功
     */
    Boolean insertData(Banner banner);

    /**
     * 更新轮播图
     *
     * @param banner 轮播图信息
     * @return 是否更新成功
     */
    Boolean updateById(Banner banner);

    /**
     * 移除轮播图
     *
     * @param bannerId 轮播图编号
     * @return 是否移除成功
     */
    Boolean deleteById(Integer bannerId);
}
