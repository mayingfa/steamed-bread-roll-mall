package com.qiu.controller;

import com.qiu.entity.Product;
import com.qiu.entity.Purchase;
import com.qiu.entity.Supplier;
import com.qiu.service.ProductService;
import com.qiu.service.PurchaseService;
import com.qiu.service.SupplierService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 8:23
 * @description 供应商和采购
 */
@CrossOrigin
@RestController
public class PurchaseController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;


    /**
     * 查询供应商
     *
     * @param supplierId 供应商编号
     */
    @RequestMapping(value = "/supplier/findById")
    public CommonResult findById(Integer supplierId) {
        Supplier supplier = supplierService.selectById(supplierId);
        if (supplier != null) {
            return CommonResult.success("供货商查询成功", supplier);
        }
        return CommonResult.error("供货商查询失败");
    }

    /**
     * 查询全部供应商
     */
    @RequestMapping(value = "/supplier/findAll")
    public CommonResult findAll() {
        List<Supplier> suppliers = supplierService.selectAll();
        if (suppliers != null) {
            return CommonResult.success("供货商查询成功", suppliers);
        }
        return CommonResult.error("供货商查询失败");
    }

    /**
     * 查询全部可用供应商
     */
    @RequestMapping(value = "/supplier/findAllUsable")
    public CommonResult findAllUsable() {
        List<Supplier> suppliers = supplierService.selectAllUsable();
        if (suppliers != null) {
            return CommonResult.success("供货商查询成功", suppliers);
        }
        return CommonResult.error("供货商查询失败");
    }

    /**
     * 查询全部供应商名称
     */
    @RequestMapping(value = "/supplier/findAllName")
    public CommonResult findAllName() {
        List<String> names = supplierService.selectAllName();
        if (names != null) {
            return CommonResult.success("供货商名称查询成功", names);
        }
        return CommonResult.error("供货商名称查询失败");
    }

    /**
     * 查询供应商是否存在
     *
     * @param supplierId   供应商编号
     * @param supplierName 供应商名称
     */
    @RequestMapping(value = "/supplier/existSupplier")
    public CommonResult existSupplier(Integer supplierId, String supplierName) {
        Boolean exist = supplierService.existsWithSupplierName(supplierId, supplierName);
        return CommonResult.success("查询成功", exist);
    }

    /**
     * 添加供应商
     *
     * @param supplier 供应商信息
     */
    @RequestMapping(value = "/supplier/addSupplier")
    public CommonResult addSupplier(Supplier supplier) {
        if (supplier != null) {
            if (supplierService.insertData(supplier)) {
                return CommonResult.success("添加成功", supplier);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("供应商数据不存在");
    }

    /**
     * 更新供应商信息
     *
     * @param supplier 供应商信息
     */
    @RequestMapping(value = "/supplier/updateSupplier")
    public CommonResult updateSupplier(Supplier supplier) {
        if (supplier != null) {
            if (supplierService.updateById(supplier)) {
                return CommonResult.success("更新成功", supplier);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("供应商数据不存在");
    }

    /**
     * 删除供应商
     *
     * @param supplierId 供应商编号
     */
    @RequestMapping(value = "/supplier/deleteSupplier")
    public CommonResult deleteSupplier(Integer supplierId) {
        if (supplierId != null) {
            if (supplierService.deleteById(supplierId)) {
                return CommonResult.success("删除成功", supplierId);
            }
            return CommonResult.error("删除失败");
        }
        return CommonResult.error("供应商数据不存在");
    }

    /**
     * 查询采购信息
     *
     * @param purchaseId 采购编号
     */
    @RequestMapping(value = "/purchase/findPurchaseById")
    public CommonResult findPurchaseById(Integer purchaseId) {
        Purchase purchase = purchaseService.selectById(purchaseId);
        if (purchase != null) {
            return CommonResult.success("采购信息查询成功", purchase);
        }
        return CommonResult.error("采购信息查询失败");
    }

    /**
     * 查询全部采购信息
     */
    @RequestMapping(value = "/purchase/findPurchaseAll")
    public CommonResult findPurchaseAll() {
        List<Purchase> purchases = purchaseService.selectAll();
        if (purchases != null) {
            return CommonResult.success("采购信息查询成功", purchases);
        }
        return CommonResult.error("采购信息查询失败");
    }

    /**
     * 查询采购编号是否存在
     *
     * @param purchaseId 采购编号
     */
    @RequestMapping(value = "/purchase/existPurchase")
    public CommonResult existPurchase(String purchaseId) {
        Boolean isExist = purchaseService.existsWithPrimaryKey(purchaseId);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 添加采购信息
     *
     * @param purchase 采购信息
     */
    @RequestMapping(value = "/purchase/addPurchase")
    public CommonResult addPurchase(Purchase purchase) {
        if (purchase != null) {
            if (purchaseService.insertData(purchase)) {
                return CommonResult.success("添加成功", purchase);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("采购信息数据不存在");
    }

    /**
     * 更新采购信息
     *
     * @param purchase 采购信息
     */
    @RequestMapping(value = "/purchase/updatePurchase")
    public CommonResult updatePurchase(Purchase purchase) {
        if (purchase != null) {
            if (purchaseService.updateById(purchase)) {
                return CommonResult.success("更新成功", purchase);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("采购信息数据不存在");
    }

    /**
     * 删除采购信息
     *
     * @param purchaseId 采购编号
     */
    @RequestMapping(value = "/purchase/deletePurchase")
    public CommonResult deletePurchase(Integer purchaseId) {
        if (purchaseId != null) {
            if (purchaseService.deleteById(purchaseId)) {
                return CommonResult.success("删除成功", purchaseId);
            }
            return CommonResult.error("删除失败");
        }
        return CommonResult.error("采购信息数据不存在");
    }

    /**
     * 商品采购收货
     *
     * @param productNo      商品编号
     * @param purchaseId     采购编号
     * @param purchaseNumber 采购数量
     */
    @RequestMapping(value = "/purchase/receipts")
    public CommonResult deletePurchase(String productNo, Integer purchaseId, Integer purchaseNumber) {
        Integer productId = productService.selectIdByKey(productNo);
        Product product = productService.selectById(productId);
        Integer lowestStock = product.getLowestStock();
        Integer productStock = product.getProductStock();
        product.setProductStock(productStock + purchaseNumber);
        product.setIsStockOut(product.getProductStock() < lowestStock);
        if (productService.updateById(product)) {
            Purchase purchase = new Purchase();
            purchase.setPurchaseId(purchaseId);
            purchase.setReceiptStatus(true);
            purchase.setReceiptTime(new Date());
            if (purchaseService.updateById(purchase)) {
                return CommonResult.success("收货成功", purchaseId);
            }
            return CommonResult.error("采购信息更新失败");
        }
        return CommonResult.error("商品库存更新失败");
    }
}
