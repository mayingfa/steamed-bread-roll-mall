package com.qiu.controller;

import com.qiu.entity.ShoppingCart;
import com.qiu.service.ShoppingCartService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/12/31 16:23
 * @description 购物车业务类
 */
@RestController
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 新增商品到购物车
     *
     * @param shoppingCart 购物车商品信息
     */
    @RequestMapping(value = "/shoppingCart/add")
    public CommonResult addShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCartService.insertData(shoppingCart)) {
            return CommonResult.success("购物车添加成功", shoppingCart);
        }
        return CommonResult.error("购物车添加失败");
    }

    /**
     * 更新购物车商品
     *
     * @param shoppingCart 购物车商品信息
     */
    @RequestMapping(value = "/shoppingCart/update")
    public CommonResult updateShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCartService.updateById(shoppingCart)) {
            return CommonResult.success("购物车修改成功", shoppingCart);
        }
        return CommonResult.error("购物车修改失败");
    }

    /**
     * 购物车移除商品
     *
     * @param cartId 购物车商品编号
     */
    @RequestMapping(value = "/shoppingCart/deleteById")
    public CommonResult deleteShoppingCart(Integer cartId) {
        if (shoppingCartService.deleteById(cartId)) {
            return CommonResult.success("购物车删除成功", "cartId: " + cartId);
        }
        return CommonResult.error("购物车删除失败");
    }

    /**
     * 根据用户移除购物车
     *
     * @param account 用户账户
     */
    @RequestMapping(value = "/shoppingCart/deleteByUser")
    public CommonResult deleteByUser(String account) {
        if (shoppingCartService.deleteByUser(account)) {
            return CommonResult.success("购物车删除成功", account);
        }
        return CommonResult.error("购物车删除失败");
    }


    /**
     * 查询用户账号下的购物车信息
     *
     * @param account 用户账户
     */
    @RequestMapping(value = "/shoppingCart/findAll")
    public CommonResult findAllShoppingCart(String account) {
        List<Map<String, Object>> shoppingInfo = shoppingCartService.selectAll(account);
        if (shoppingInfo != null) {
            return CommonResult.success("购物车查询成功", shoppingInfo);
        }
        return CommonResult.error("购物车查询失败");
    }

    /**
     * 根据购物车商品编号查询购物车商品信息
     *
     * @param cartId 购物车商品编号
     */
    @RequestMapping(value = "/shoppingCart/findById")
    public CommonResult findById(Integer cartId) {
        ShoppingCart shoppingCart = shoppingCartService.selectById(cartId);
        if (shoppingCart != null) {
            return CommonResult.success("购物车查询成功", shoppingCart);
        }
        return CommonResult.error("购物车查询失败");
    }
}
