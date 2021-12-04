package com.qiu.service;

import com.qiu.entity.Banner;
import com.qiu.entity.ProductType;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:43
 * @description 商品轮播图 service 接口
 */
public interface BannerService {
    //通过ID查询
    Banner selectById(Integer bannerId);
    //查询所有
    List<Banner> selectAll();

    //插入一条数据
    Boolean insertData(Banner banner);
    //通过ID更新
    Boolean updateById(Banner banner);
    //通过ID删除
    Boolean deleteById(Integer bannerId);
}
