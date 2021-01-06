package com.qiu.service;

import com.qiu.dao.BannerDao;
import com.qiu.entity.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:46
 * @description 商品轮播图 service 实现类
 */

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    private final BannerDao bannerDao;
    public BannerServiceImpl(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Banner selectById(Integer bannerId) {
        return bannerDao.selectById(bannerId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> selectAll() {
        return bannerDao.selectAll();
    }

    @Override
    public Boolean insertData(Banner banner) {
        return bannerDao.insertData(banner);
    }

    @Override
    public Boolean updateById(Banner banner) {
        return bannerDao.updateById(banner);
    }

    @Override
    public Boolean deleteById(Integer bannerId) {
        return bannerDao.deleteById(bannerId);
    }



}
