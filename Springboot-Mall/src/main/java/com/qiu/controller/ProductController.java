package com.qiu.controller;
import com.qiu.entity.Product;
import com.qiu.entity.ProductBrand;
import com.qiu.entity.ProductType;
import com.qiu.service.ProductBrandService;
import com.qiu.service.ProductService;
import com.qiu.service.ProductTypeService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/28 18:11
 * @description 商品相关业务
 */

@RestController
@CrossOrigin
public class ProductController {
    final ProductTypeService productTypeService;
    final ProductBrandService productBrandService;
    final ProductService productService;
    public ProductController(ProductService productService, ProductTypeService productTypeService,ProductBrandService productBrandService) {
        this.productTypeService = productTypeService;
        this.productBrandService = productBrandService;
        this.productService = productService;
    }

    /*商品类别*/
    @RequestMapping(value = "/product/findById")
    private CommonResult findById(Integer productId) {
        Product product = productService.selectById(productId);
        if(product!=null){
            return CommonResult.success("商品查询成功",product);
        }else{
            return CommonResult.error("商品查询失败");
        }
    }
    @RequestMapping(value = "/product/findByKey")
    private CommonResult findByKey(String productNo) {
        Product product = productService.selectByKey(productNo);
        if(product!=null){
            return CommonResult.success("商品查询成功",product);
        }else{
            return CommonResult.error("商品查询失败");
        }
    }
    @RequestMapping(value = "/product/findIdByKey")
    private CommonResult findIdByKey(String productNo) {
        Integer productId = productService.selectIdByKey(productNo);
        if(productId!=null){
            return CommonResult.success("商品id查询成功",productId);
        }else{
            return CommonResult.error("商品id查询失败");
        }
    }
    @RequestMapping(value = "/product/findCount")
    private CommonResult findCount() {
        Integer count = productService.selectCount();
        if(count!=null){
            return CommonResult.success("商品数量查询成功",count);
        }else{
            return CommonResult.error("商品数量查询失败");
        }
    }
    @RequestMapping(value = "/product/existsKey")
    private CommonResult existsKey(String productNo) {
        Boolean isExist = productService.existsWithPrimaryKey(productNo);
        if(isExist!=null){
            return CommonResult.success("商品是否存在查询成功",isExist);
        }else{
            return CommonResult.error("商品是否存在查询失败");
        }
    }
    @RequestMapping(value = "/product/existsType")
    private CommonResult existsType(String productType) {
        Boolean isExist = productService.existsProductType(productType);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    @RequestMapping(value = "/product/existsBrand")
    private CommonResult existsBrand(String productBrand) {
        Boolean isExist = productService.existsProductBrand(productBrand);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    @RequestMapping(value = "/product/findAll")
    private CommonResult findAll() {
        List<Product> products = productService.selectAll();
        if(products!=null){
            return CommonResult.success("全部商品信息查询成功",products);
        }else{
            return CommonResult.error("全部商品信息查询失败");
        }
    }
    @RequestMapping(value = "/product/findAllSale")
    private CommonResult findAllSale() {
        List<Product> products = productService.selectAllSale();
        if(products!=null){
            return CommonResult.success("全部商品信息查询成功",products);
        }else{
            return CommonResult.error("全部商品信息查询失败");
        }
    }
    @RequestMapping(value = "/product/findAllLikeName")
    private CommonResult findAllLikeName(String keyWord) {
        List<Product> products = productService.selectAllLikeName(keyWord);
        if(products!=null){
            return CommonResult.success("全部商品信息查询成功",products);
        }else{
            return CommonResult.error("全部商品信息查询失败");
        }
    }
    @RequestMapping(value = "/product/findAllLikeType")
    private CommonResult findAllLikeType(String keyWord) {
        List<Product> products = productService.selectAllLikeType(keyWord);
        if(products!=null){
            return CommonResult.success("全部商品信息查询成功",products);
        }else{
            return CommonResult.error("全部商品信息查询失败");
        }
    }
    @RequestMapping(value = "/product/findAllLikeBrand")
    private CommonResult findAllLikeBrand(String keyWord) {
        List<Product> products = productService.selectAllLikeBrand(keyWord);
        if(products!=null){
            return CommonResult.success("全部商品信息查询成功",products);
        }else{
            return CommonResult.error("全部商品信息查询失败");
        }
    }
    @RequestMapping(value = "/product/findAllByType")
    private CommonResult findAllByType() {
        List<Map<String, Object>> maps = productService.selectAllByType();
        if(maps!=null){
            return CommonResult.success("商品分类信息查询成功",maps);
        }else{
            return CommonResult.error("商品分类信息查询失败");
        }
    }

    @RequestMapping(value = "/product/add")
    private CommonResult add(Product product) {
        System.out.println(product);
        if(productService.insertData(product)){
            return CommonResult.success("添加商品成功",product);
        }else{
            return CommonResult.error("添加商品失败");
        }
    }

    @RequestMapping(value = "/product/update")
    private CommonResult update(Product product) {
        if(product.getIsNew()!=null && product.getIsNew()){
            product.setSaleTime(new Date());
        }
        if(productService.updateById(product)){
            return CommonResult.success("修改商品成功",product);
        }else{
            return CommonResult.error("修改商品失败");
        }
    }

    @RequestMapping(value = "/product/delete")
    private CommonResult delete(Integer productId) {
        if(productService.deleteById(productId)){
            return CommonResult.success("商品删除成功","productId：" + productId);
        }else{
            return CommonResult.error("商品删除失败");
        }
    }

    /*商品类别*/
    @RequestMapping(value = "/productType/add")
    private CommonResult addType(ProductType productType) {
        if(productTypeService.insertData(productType)){
            return CommonResult.success("商品分类添加成功",productType);
        }else{
            return CommonResult.error("商品分类添加失败");
        }
    }

    @RequestMapping(value = "/productType/update")
    private CommonResult updateType(ProductType productType) {
        if(productTypeService.updateById(productType)){
            return CommonResult.success("商品分类修改成功",productType);
        }else{
            return CommonResult.error("商品分类修改失败");
        }
    }

    @RequestMapping(value = "/productType/deleteById")
    private CommonResult deleteTypeById(Integer typeId) {
        if(productTypeService.deleteById(typeId)){
            return CommonResult.success("商品分类删除成功","typeId: "+typeId);
        }else{
            return CommonResult.error("商品分类删除失败");
        }
    }

    @RequestMapping(value = "/productType/deleteByName")
    private CommonResult deleteTypeByName(String typeName) {
        if(productTypeService.deleteByName(typeName)){
            return CommonResult.success("商品分类删除成功","typeName: "+typeName);
        }else{
            return CommonResult.error("商品分类删除失败");
        }
    }

    @RequestMapping(value = "/productType/existsTypeName")
    private CommonResult existsTypeName(Integer typeId,String typeName) {
        Boolean isExist = productTypeService.existsWithTypeName(typeId,typeName);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/productType/findAll")
    private CommonResult findAllType() {
        List<ProductType> productTypes = productTypeService.selectAll();
        if(productTypes!=null){
            return CommonResult.success("商品分类查询成功",productTypes);
        }else{
            return CommonResult.error("商品分类查询失败");
        }
    }

    @RequestMapping(value = "/productType/findAllName")
    private CommonResult findAllTypeName() {
        List<String> names = productTypeService.selectAllName();
        if(names!=null){
            return CommonResult.success("商品分类名称查询成功",names);
        }else{
            return CommonResult.error("商品分类名称查询失败");
        }
    }

    /*商品品牌*/
    @RequestMapping(value = "/productBrand/add")
    private CommonResult addBrand(ProductBrand productBrand) {
        if(productBrandService.insertData(productBrand)){
            return CommonResult.success("商品品牌添加成功",productBrand);
        }else{
            return CommonResult.error("商品品牌添加失败");
        }
    }

    @RequestMapping(value = "/productBrand/update")
    private CommonResult updateBrand(ProductBrand productBrand) {
        if(productBrandService.updateById(productBrand)){
            return CommonResult.success("商品品牌修改成功",productBrand);
        }else{
            return CommonResult.error("商品品牌修改失败");
        }
    }

    @RequestMapping(value = "/productBrand/deleteById")
    private CommonResult deleteBrandById(Integer brandId) {
        if(productBrandService.deleteById(brandId)){
            return CommonResult.success("商品品牌删除成功","brandId: "+brandId);
        }else{
            return CommonResult.error("商品品牌删除失败");
        }
    }

    @RequestMapping(value = "/productBrand/deleteByName")
    private CommonResult deleteBrandByName(String brandName) {
        if(productBrandService.deleteByName(brandName)){
            return CommonResult.success("商品品牌删除成功","brandName: "+brandName);
        }else{
            return CommonResult.error("商品品牌删除失败");
        }
    }

    @RequestMapping(value = "/productBrand/findAll")
    private CommonResult findAllBrand() {
        List<ProductBrand> productBrands = productBrandService.selectAll();
        if(productBrands!=null){
            return CommonResult.success("商品品牌查询成功",productBrands);
        }else{
            return CommonResult.error("商品品牌查询失败");
        }
    }

    @RequestMapping(value = "/productBrand/existsBrandName")
    private CommonResult existsBrandName(Integer brandId,String brandName) {
        Boolean isExist = productBrandService.existsWithBrandName(brandId,brandName);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/productBrand/findAllName")
    private CommonResult findAllBrandName() {
        List<String> names = productBrandService.selectAllName();
        if(names!=null){
            return CommonResult.success("商品品牌名称查询成功",names);
        }else{
            return CommonResult.error("商品品牌名称查询失败");
        }
    }
}
