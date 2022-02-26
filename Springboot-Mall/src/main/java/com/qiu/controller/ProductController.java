package com.qiu.controller;

import com.qiu.entity.Product;
import com.qiu.entity.ProductBrand;
import com.qiu.entity.ProductType;
import com.qiu.service.ProductBrandService;
import com.qiu.service.ProductService;
import com.qiu.service.ProductTypeService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/28 18:11
 * @description 商品相关业务
 */

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/findById")
    public CommonResult findById(Integer productId) {
        Product product = productService.selectById(productId);
        if (product != null) {
            return CommonResult.success("商品查询成功", product);
        }
        return CommonResult.error("商品查询失败");
    }

    @RequestMapping(value = "/product/findByKey")
    public CommonResult findByKey(String productNo) {
        Product product = productService.selectByKey(productNo);
        if (product != null) {
            return CommonResult.success("商品查询成功", product);
        }
        return CommonResult.error("商品查询失败");
    }

    @RequestMapping(value = "/product/findIdByKey")
    public CommonResult findIdByKey(String productNo) {
        Integer productId = productService.selectIdByKey(productNo);
        if (productId != null) {
            return CommonResult.success("商品id查询成功", productId);
        }
        return CommonResult.error("商品id查询失败");
    }

    @RequestMapping(value = "/product/findCount")
    public CommonResult findCount() {
        int count = productService.selectCount();
        return CommonResult.success("商品数量查询成功", count);
    }

    @RequestMapping(value = "/product/existsKey")
    public CommonResult existsKey(String productNo) {
        boolean exist = productService.existsWithPrimaryKey(productNo);
        return CommonResult.success("商品是否存在查询成功", exist);
    }

    @RequestMapping(value = "/product/existsType")
    public CommonResult existsType(String productType) {
        boolean exist = productService.existsProductType(productType);
        return CommonResult.success("查询成功", exist);
    }

    @RequestMapping(value = "/product/existsBrand")
    public CommonResult existsBrand(String productBrand) {
        boolean exist = productService.existsProductBrand(productBrand);
        return CommonResult.success("查询成功", exist);
    }

    @RequestMapping(value = "/product/findAll")
    public CommonResult findAll() {
        List<Product> products = productService.selectAll();
        if (products != null) {
            return CommonResult.success("全部商品信息查询成功", products);
        }
        return CommonResult.error("全部商品信息查询失败");
    }

    @RequestMapping(value = "/product/findAllSale")
    public CommonResult findAllSale() {
        List<Product> products = productService.selectAllSale();
        if (products != null) {
            return CommonResult.success("全部商品信息查询成功", products);
        }
        return CommonResult.error("全部商品信息查询失败");
    }

    @RequestMapping(value = "/product/findAllLikeName")
    public CommonResult findAllLikeName(String keyWord) {
        List<Product> products = productService.selectAllLikeName(keyWord);
        if (products != null) {
            return CommonResult.success("全部商品信息查询成功", products);
        }
        return CommonResult.error("全部商品信息查询失败");
    }

    @RequestMapping(value = "/product/findAllLikeType")
    public CommonResult findAllLikeType(String keyWord) {
        List<Product> products = productService.selectAllLikeType(keyWord);
        if (products != null) {
            return CommonResult.success("全部商品信息查询成功", products);
        }
        return CommonResult.error("全部商品信息查询失败");
    }

    @RequestMapping(value = "/product/findAllLikeBrand")
    public CommonResult findAllLikeBrand(String keyWord) {
        List<Product> products = productService.selectAllLikeBrand(keyWord);
        if (products != null) {
            return CommonResult.success("全部商品信息查询成功", products);
        }
        return CommonResult.error("全部商品信息查询失败");
    }

    @RequestMapping(value = "/product/findAllByType")
    public CommonResult findAllByType() {
        List<Map<String, Object>> maps = productService.selectAllByType();
        if (maps != null) {
            return CommonResult.success("商品分类信息查询成功", maps);
        }
        return CommonResult.error("商品分类信息查询失败");
    }

    @RequestMapping(value = "/product/add")
    public CommonResult add(Product product) {
        if (productService.insertData(product)) {
            return CommonResult.success("添加商品成功", product);
        }
        return CommonResult.error("添加商品失败");
    }

    @RequestMapping(value = "/product/update")
    public CommonResult update(Product product) {
        if (product.getIsNew() != null && product.getIsNew()) {
            product.setSaleTime(new Date());
        }
        if (productService.updateById(product)) {
            return CommonResult.success("修改商品成功", product);
        }
        return CommonResult.error("修改商品失败");
    }

    @RequestMapping(value = "/product/delete")
    public CommonResult delete(Integer productId) {
        if (productService.deleteById(productId)) {
            return CommonResult.success("商品删除成功", productId);
        }
        return CommonResult.error("商品删除失败");
    }

    /**
     * 商品类别
     */
    @RequestMapping(value = "/productType/add")
    public CommonResult addType(ProductType productType) {
        if (productTypeService.insertData(productType)) {
            return CommonResult.success("商品分类添加成功", productType);
        }
        return CommonResult.error("商品分类添加失败");
    }

    @RequestMapping(value = "/productType/update")
    public CommonResult updateType(ProductType productType) {
        if (productTypeService.updateById(productType)) {
            return CommonResult.success("商品分类修改成功", productType);
        } else {
            return CommonResult.error("商品分类修改失败");
        }
    }

    @RequestMapping(value = "/productType/deleteById")
    public CommonResult deleteTypeById(Integer typeId) {
        if (productTypeService.deleteById(typeId)) {
            return CommonResult.success("商品分类删除成功", typeId);
        }
        return CommonResult.error("商品分类删除失败");
    }

    @RequestMapping(value = "/productType/deleteByName")
    public CommonResult deleteTypeByName(String typeName) {
        if (productTypeService.deleteByName(typeName)) {
            return CommonResult.success("商品分类删除成功", typeName);
        }
        return CommonResult.error("商品分类删除失败");
    }

    @RequestMapping(value = "/productType/existsTypeName")
    public CommonResult existsTypeName(Integer typeId, String typeName) {
        boolean exist = productTypeService.existsWithTypeName(typeId, typeName);
        return CommonResult.success("查询成功", exist);
    }

    @RequestMapping(value = "/productType/findAll")
    public CommonResult findAllType() {
        List<ProductType> productTypes = productTypeService.selectAll();
        if (productTypes != null) {
            return CommonResult.success("商品分类查询成功", productTypes);
        }
        return CommonResult.error("商品分类查询失败");
    }

    @RequestMapping(value = "/productType/findAllName")
    public CommonResult findAllTypeName() {
        List<String> names = productTypeService.selectAllName();
        if (names != null) {
            return CommonResult.success("商品分类名称查询成功", names);
        }
        return CommonResult.error("商品分类名称查询失败");
    }

    /**
     * 商品品牌
     */
    @RequestMapping(value = "/productBrand/add")
    public CommonResult addBrand(ProductBrand productBrand) {
        if (productBrandService.insertData(productBrand)) {
            return CommonResult.success("商品品牌添加成功", productBrand);
        }
        return CommonResult.error("商品品牌添加失败");
    }

    @RequestMapping(value = "/productBrand/update")
    public CommonResult updateBrand(ProductBrand productBrand) {
        if (productBrandService.updateById(productBrand)) {
            return CommonResult.success("商品品牌修改成功", productBrand);
        }
        return CommonResult.error("商品品牌修改失败");
    }

    @RequestMapping(value = "/productBrand/deleteById")
    public CommonResult deleteBrandById(Integer brandId) {
        if (productBrandService.deleteById(brandId)) {
            return CommonResult.success("商品品牌删除成功", brandId);
        }
        return CommonResult.error("商品品牌删除失败");
    }

    @RequestMapping(value = "/productBrand/deleteByName")
    public CommonResult deleteBrandByName(String brandName) {
        if (productBrandService.deleteByName(brandName)) {
            return CommonResult.success("商品品牌删除成功", brandName);
        }
        return CommonResult.error("商品品牌删除失败");
    }

    @RequestMapping(value = "/productBrand/findAll")
    public CommonResult findAllBrand() {
        List<ProductBrand> productBrands = productBrandService.selectAll();
        if (productBrands != null) {
            return CommonResult.success("商品品牌查询成功", productBrands);
        }
        return CommonResult.error("商品品牌查询失败");
    }

    @RequestMapping(value = "/productBrand/existsBrandName")
    public CommonResult existsBrandName(Integer brandId, String brandName) {
        boolean exist = productBrandService.existsWithBrandName(brandId, brandName);
        return CommonResult.success("查询成功", exist);
    }

    @RequestMapping(value = "/productBrand/findAllName")
    public CommonResult findAllBrandName() {
        List<String> names = productBrandService.selectAllName();
        if (names != null && !names.isEmpty()) {
            return CommonResult.success("商品品牌名称查询成功", names);
        }
        return CommonResult.error("商品品牌名称查询失败");
    }
}
