package com.qiu.controller;

import com.qiu.entity.ProductSpecs;
import com.qiu.entity.Specs;
import com.qiu.service.ProductSpecsService;
import com.qiu.service.SpecsService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:15
 * @description 商品规格、商品与商品规格的关联
 */

@CrossOrigin
@RestController
public class SpecsController {

    @Autowired
    private SpecsService specsService;

    @Autowired
    private ProductSpecsService productSpecsService;


    /**
     * 根据id查询规格
     *
     * @param specsId 规格编号
     */
    @RequestMapping(value = "/specs/findById")
    CommonResult findById(Integer specsId) {
        Specs specs = specsService.selectById(specsId);
        if (specs != null) {
            return CommonResult.success("查询成功", specs);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询所有规格信息
     */
    @RequestMapping(value = "/specs/findAll")
    CommonResult findAllSpecs() {
        List<Specs> specs = specsService.selectAll();
        if (specs != null) {
            return CommonResult.success("查询成功", specs);
        }
        return CommonResult.error("查询失败");
    }

    @RequestMapping(value = "/specs/existsSpecsName")
    CommonResult existsSpecsName(Integer specsId, String specsName, String productType) {
        Boolean isExist = specsService.existsWithSpecsName(specsId, specsName, productType);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        }
        return CommonResult.error("查询失败");
    }

    @RequestMapping(value = "/specs/findAllByType")
    CommonResult findAllSpecsByType(String productType) {
        List<Specs> specs = specsService.selectAllByType(productType);
        if (specs != null) {
            return CommonResult.success("查询成功", specs);
        }
        return CommonResult.error("查询失败");
    }

    @RequestMapping(value = "/specs/add")
    CommonResult addSpecs(Specs specs) {
        if (specs != null) {
            if (specsService.insertData(specs)) {
                return CommonResult.success("添加成功", specs);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("数据不存在");
    }

    @RequestMapping(value = "/specs/update")
    CommonResult updateSpecs(Specs specs) {
        if (specs != null) {
            if (specsService.updateById(specs)) {
                return CommonResult.success("更新成功", specs);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("数据不存在");
    }

    @RequestMapping(value = "/specs/delete")
    CommonResult deleteSpecs(Integer specsId) {
        if (specsService.deleteById(specsId)) {
            return CommonResult.success("删除成功", specsId);
        }
        return CommonResult.error("删除失败");
    }


    /**
     * 查询所有商品规格对应信息
     */
    @RequestMapping(value = "/productSpecs/findAll")
    CommonResult findAll() {
        List<ProductSpecs> productSpecs = productSpecsService.selectAll();
        if (productSpecs != null) {
            return CommonResult.success("查询成功", productSpecs);
        }
        return CommonResult.error("查询失败");
    }


    @RequestMapping(value = "/productSpecs/findAllByProId")
    CommonResult findAllByProId(Integer productId) {
        List<String> specsName = productSpecsService.selectAllByProId(productId);
        if (specsName != null) {
            return CommonResult.success("查询成功", specsName);
        }
        return CommonResult.error("查询失败");
    }

    @RequestMapping(value = "/productSpecs/add")
    CommonResult add(ProductSpecs productSpecs) {
        if (productSpecs != null) {
            if (productSpecsService.insertData(productSpecs)) {
                return CommonResult.success("添加成功", productSpecs);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("数据不存在");
    }

    @RequestMapping(value = "/productSpecs/addBatch")
    CommonResult addBatch(Integer productId, Integer[] specsIds) {
        if (specsIds != null) {
            ProductSpecs productSpecs;
            List<ProductSpecs> productSpecsList = new ArrayList<>();
            for (Integer specsId : specsIds) {
                productSpecs = new ProductSpecs();
                productSpecs.setProductId(productId);
                productSpecs.setSpecsId(specsId);
                productSpecsList.add(productSpecs);
            }
            if (productSpecsService.insertBatch(productSpecsList)) {
                return CommonResult.success("添加成功", productSpecsList);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("数据不存在");
    }

    @RequestMapping(value = "/productSpecs/update")
    CommonResult update(ProductSpecs productSpecs) {
        if (productSpecsService.updateById(productSpecs)) {
            return CommonResult.success("更新成功", productSpecs);
        }
        return CommonResult.error("更新失败");
    }

    @RequestMapping(value = "/productSpecs/delete")
    CommonResult delete(ProductSpecs productSpecs) {
        if (productSpecsService.deleteById(productSpecs)) {
            return CommonResult.success("删除成功", productSpecs);
        }
        return CommonResult.error("删除失败");
    }
}
