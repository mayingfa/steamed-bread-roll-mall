package com.qiu.controller;

import com.qiu.entity.Banner;
import com.qiu.service.BannerService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/12/31 16:23
 * @description 描述
 */
@RestController
@CrossOrigin
public class BannerController {
    final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /*商品类别*/
    @RequestMapping(value = "/banner/add")
    public CommonResult addBanner(Banner banner) {
        if (bannerService.insertData(banner)) {
            return CommonResult.success("商品轮播图添加成功", banner);
        } else {
            return CommonResult.error("商品轮播图添加失败");
        }
    }

    @RequestMapping(value = "/banner/update")
    public CommonResult updateType(Banner banner) {
        if (bannerService.updateById(banner)) {
            return CommonResult.success("商品轮播图修改成功", banner);
        } else {
            return CommonResult.error("商品轮播图修改失败");
        }
    }

    @RequestMapping(value = "/banner/deleteById")
    public CommonResult deleteTypeById(Integer bannerId) {
        if (bannerService.deleteById(bannerId)) {
            return CommonResult.success("商品轮播图删除成功", "bannerId: " + bannerId);
        } else {
            return CommonResult.error("商品轮播图删除失败");
        }
    }


    @RequestMapping(value = "/banner/findAll")
    public CommonResult findAllType() {
        List<Banner> banners = bannerService.selectAll();
        if (banners != null) {
            return CommonResult.success("商品轮播图查询成功", banners);
        } else {
            return CommonResult.error("商品轮播图查询失败");
        }
    }

    @RequestMapping(value = "/banner/findById")
    public CommonResult findById(Integer bannerId) {
        Banner banner = bannerService.selectById(bannerId);
        if (banner != null) {
            return CommonResult.success("商品轮播图查询成功", banner);
        } else {
            return CommonResult.error("商品轮播图查询失败");
        }
    }

}
