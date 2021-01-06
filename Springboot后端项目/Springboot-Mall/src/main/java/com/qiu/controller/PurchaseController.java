package com.qiu.controller;
import com.qiu.entity.Product;
import com.qiu.entity.Purchase;
import com.qiu.entity.Supplier;
import com.qiu.service.ProductService;
import com.qiu.service.PurchaseService;
import com.qiu.service.SupplierService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:23
 * @description 供应商和采购 业务控制层
 */
@RestController
@CrossOrigin
public class PurchaseController {
    final SupplierService supplierService;
    final PurchaseService purchaseService;
    final ProductService productService;
    public PurchaseController(ProductService productService,SupplierService supplierService,PurchaseService purchaseService){
        this.supplierService = supplierService;
        this.purchaseService = purchaseService;
        this.productService = productService;
    }

    /*查询供应商*/
    @RequestMapping(value = "/supplier/findById")
    private CommonResult findById(Integer supplierId) {
        Supplier supplier = supplierService.selectById(supplierId);
        if(supplier!=null){
            return CommonResult.success("供货商查询成功",supplier);
        }else{
            return CommonResult.error("供货商查询失败");
        }
    }
    /*查询全部供应商*/
    @RequestMapping(value = "/supplier/findAll")
    private CommonResult findAll() {
        List<Supplier> suppliers = supplierService.selectAll();
        if(suppliers!=null){
            return CommonResult.success("供货商查询成功",suppliers);
        }else{
            return CommonResult.error("供货商查询失败");
        }
    }
    /*查询全部供应商*/
    @RequestMapping(value = "/supplier/findAllUsable")
    private CommonResult findAllUsable() {
        List<Supplier> suppliers = supplierService.selectAllUsable();
        if(suppliers!=null){
            return CommonResult.success("供货商查询成功",suppliers);
        }else{
            return CommonResult.error("供货商查询失败");
        }
    }
    /*查询全部供应商名称*/
    @RequestMapping(value = "/supplier/findAllName")
    private CommonResult findAllName() {
        List<String> names = supplierService.selectAllName();
        if(names!=null){
            return CommonResult.success("供货商名称查询成功",names);
        }else{
            return CommonResult.error("供货商名称查询失败");
        }
    }
    /*查询供应商是否存在*/
    @RequestMapping(value = "/supplier/existSupplier")
    private CommonResult existSupplier(Integer supplierId,String supplierName) {
        Boolean isExist = supplierService.existsWithSupplierName(supplierId,supplierName);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    /*添加供应商*/
    @RequestMapping(value = "/supplier/addSupplier")
    private CommonResult addSupplier(Supplier supplier) {
        if(supplier!=null){
            if(supplierService.insertData(supplier)){
                return CommonResult.success("添加成功",supplier);
            }else{
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("供应商数据不存在");
    }
    /*修改供应商*/
    @RequestMapping(value = "/supplier/updateSupplier")
    private CommonResult updateSupplier(Supplier supplier) {
        if(supplier!=null){
            if(supplierService.updateById(supplier)){
                return CommonResult.success("更新成功",supplier);
            }else{
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("供应商数据不存在");
    }
    /*删除供应商*/
    @RequestMapping(value = "/supplier/deleteSupplier")
    private CommonResult deleteSupplier(Integer supplierId) {
        if(supplierId!=null){
            if(supplierService.deleteById(supplierId)){
                return CommonResult.success("删除成功",supplierId);
            }else{
                return CommonResult.error("删除失败");
            }
        }
        return CommonResult.error("供应商数据不存在");
    }

    /*查询采购信息*/
    @RequestMapping(value = "/purchase/findPurchaseById")
    private CommonResult findPurchaseById(Integer purchaseId) {
        Purchase purchase = purchaseService.selectById(purchaseId);
        if(purchase!=null){
            return CommonResult.success("采购信息查询成功",purchase);
        }else{
            return CommonResult.error("采购信息查询失败");
        }
    }
    /*查询全部采购信息*/
    @RequestMapping(value = "/purchase/findPurchaseAll")
    private CommonResult findPurchaseAll() {
        List<Purchase> purchases = purchaseService.selectAll();
        if(purchases!=null){
            return CommonResult.success("采购信息查询成功",purchases);
        }else{
            return CommonResult.error("采购信息查询失败");
        }
    }

    /*查询采购编号是否存在*/
    @RequestMapping(value = "/purchase/existPurchase")
    private CommonResult existPurchase(String purchaseNo) {
        Boolean isExist = purchaseService.existsWithPrimaryKey(purchaseNo);
        if(isExist!=null){
            return CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    /*添加采购信息*/
    @RequestMapping(value = "/purchase/addPurchase")
    private CommonResult addPurchase(Purchase purchase) {
        if(purchase!=null){
            if(purchaseService.insertData(purchase)){
                return CommonResult.success("添加成功",purchase);
            }else{
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("采购信息数据不存在");
    }
    /*修改采购信息*/
    @RequestMapping(value = "/purchase/updatePurchase")
    private CommonResult updatePurchase(Purchase purchase) {
        if(purchase!=null){
            if(purchaseService.updateById(purchase)){
                return CommonResult.success("更新成功",purchase);
            }else{
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("采购信息数据不存在");
    }
    /*删除采购信息*/
    @RequestMapping(value = "/purchase/deletePurchase")
    private CommonResult deletePurchase(Integer purchaseId) {
        if(purchaseId!=null){
            if(purchaseService.deleteById(purchaseId)){
                return CommonResult.success("删除成功",purchaseId);
            }else{
                return CommonResult.error("删除失败");
            }
        }
        return CommonResult.error("采购信息数据不存在");
    }

    /*收货*/
    @RequestMapping(value = "/purchase/receipts")
    private CommonResult deletePurchase(String productNo,Integer purchaseId,Integer purchaseNumber) {
        Integer productId = productService.selectIdByKey(productNo);
        Product product = productService.selectById(productId);
        Integer lowestStock = product.getLowestStock();
        Integer productStock = product.getProductStock();
        product.setProductStock(productStock+purchaseNumber);
        product.setIsStockOut(product.getProductStock()<lowestStock);
        if(productService.updateById(product)){
            Purchase purchase = new Purchase();
            purchase.setPurchaseId(purchaseId);
            purchase.setReceiptStatus(true);
            purchase.setReceiptTime(new Date());
            if(purchaseService.updateById(purchase)){
                return CommonResult.success("收货成功",purchaseId);
            }else{
                return CommonResult.error("采购信息更新失败");
            }
        }else{
            return CommonResult.error("商品库存更新失败");
        }
    }
}
