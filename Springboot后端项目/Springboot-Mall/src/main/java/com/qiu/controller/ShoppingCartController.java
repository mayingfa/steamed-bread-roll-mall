package com.qiu.controller;

import com.qiu.entity.ShoppingCart;
import com.qiu.service.ShoppingCartService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/12/31 16:23
 * @description 购物车业务类
 */
@RestController
@CrossOrigin
public class ShoppingCartController {
    final ShoppingCartService shoppingCartService;
    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }


    /*商品类别*/
    @RequestMapping(value = "/shoppingCart/add")
    private CommonResult addShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCartService.insertData(shoppingCart)){
            return CommonResult.success("购物车添加成功",shoppingCart);
        }else{
            return CommonResult.error("购物车添加失败");
        }
    }

    @RequestMapping(value = "/shoppingCart/update")
    private CommonResult updateShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCartService.updateById(shoppingCart)){
            return CommonResult.success("购物车修改成功",shoppingCart);
        }else{
            return CommonResult.error("购物车修改失败");
        }
    }

    @RequestMapping(value = "/shoppingCart/deleteById")
    private CommonResult deleteShoppingCart(Integer cartId) {
        if(shoppingCartService.deleteById(cartId)){
            return CommonResult.success("购物车删除成功","cartId: "+cartId);
        }else{
            return CommonResult.error("购物车删除失败");
        }
    }
    @RequestMapping(value = "/shoppingCart/deleteByUser")
    private CommonResult deleteByUser(String accountNumber) {
        if(shoppingCartService.deleteByUser(accountNumber)){
            return CommonResult.success("购物车删除成功","accountNumber: "+accountNumber);
        }else{
            return CommonResult.error("购物车删除失败");
        }
    }


    @RequestMapping(value = "/shoppingCart/findAll")
    private CommonResult findAllShoppingCart(String accountNumber) {
        List<Map<String, Object>> shoppingInfo = shoppingCartService.selectAll(accountNumber);
        if(shoppingInfo!=null){
            return CommonResult.success("购物车查询成功",shoppingInfo);
        }else{
            return CommonResult.error("购物车查询失败");
        }
    }

    @RequestMapping(value = "/shoppingCart/findById")
    private CommonResult findById(Integer cartId) {
        ShoppingCart shoppingCart = shoppingCartService.selectById(cartId);
        if(shoppingCart!=null){
            return CommonResult.success("购物车查询成功",shoppingCart);
        }else{
            return CommonResult.error("购物车查询失败");
        }
    }

}
