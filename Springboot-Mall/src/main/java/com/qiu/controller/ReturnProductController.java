package com.qiu.controller;

import com.qiu.entity.*;
import com.qiu.service.*;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 21:09
 * @description 商品退回 业务控制层
 */
@RestController
@CrossOrigin
public class ReturnProductController {
    final ReturnReasonService returnReasonService;
    final ReturnGoodsService returnGoodsService;
    final LogisticsService logisticsService;
    final OrderService orderService;
    final ProductService productService;

    public ReturnProductController(ProductService productService, OrderService orderService, ReturnReasonService returnReasonService, ReturnGoodsService returnGoodsService, LogisticsService logisticsService) {
        this.returnReasonService = returnReasonService;
        this.returnGoodsService = returnGoodsService;
        this.logisticsService = logisticsService;
        this.orderService = orderService;
        this.productService = productService;
    }

    /*退货原因*/
    @RequestMapping(value = "/returnReason/findReasonById")
    public CommonResult findReasonById(Integer reasonId) {
        ReturnReason returnReason = returnReasonService.selectById(reasonId);
        if (returnReason != null) {
            return CommonResult.success("退货原因查询成功", returnReason);
        } else {
            return CommonResult.error("退货原因查询失败");
        }
    }

    /*查询全部退货原因*/
    @RequestMapping(value = "/returnReason/findAllReason")
    public CommonResult findAllReason() {
        List<ReturnReason> returnReasons = returnReasonService.selectAll();
        if (returnReasons != null) {
            return CommonResult.success("退货原因查询成功", returnReasons);
        } else {
            return CommonResult.error("退货原因查询失败");
        }
    }

    /*查询全部退货原因名称*/
    @RequestMapping(value = "/returnReason/findReasonName")
    public CommonResult findReasonName() {
        List<String> names = returnReasonService.selectAllName();
        if (names != null) {
            return CommonResult.success("退货原因查询成功", names);
        } else {
            return CommonResult.error("退货原因查询失败");
        }
    }

    /*查询退货原因是否存在*/
    @RequestMapping(value = "/returnReason/existReasonName")
    public CommonResult existReasonName(Integer reasonId, String reasonName) {
        Boolean isExist = returnReasonService.existsWithReasonName(reasonId, reasonName);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/returnReason/addReason")
    public CommonResult addReason(ReturnReason returnReason) {
        if (returnReason != null) {
            if (returnReasonService.insertData(returnReason)) {
                return CommonResult.success("添加成功", returnReason);
            } else {
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("退货原因数据不存在");
    }

    @RequestMapping(value = "/returnReason/updateReason")
    public CommonResult updateReason(ReturnReason returnReason) {
        if (returnReason != null) {
            if (returnReasonService.updateById(returnReason)) {
                return CommonResult.success("更新成功", returnReason);
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("退货原因数据不存在");
    }

    @RequestMapping(value = "/returnReason/deleteReason")
    public CommonResult updateReason(Integer reasonId) {
        if (reasonId != null) {
            if (returnReasonService.deleteById(reasonId)) {
                return CommonResult.success("删除成功", reasonId);
            } else {
                return CommonResult.error("删除失败");
            }
        }
        return CommonResult.error("退货原因数据不存在");
    }

    /*查询商品退货信息*/
    @RequestMapping(value = "/returnGoods/findReturnById")
    public CommonResult findReturnById(Integer returnId) {
        ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
        if (returnGoods != null) {
            return CommonResult.success("退货商品查询成功", returnGoods);
        } else {
            return CommonResult.error("退货商品查询失败");
        }
    }

    /*查询全部退货商品*/
    @RequestMapping(value = "/returnGoods/findAllReturn")
    public CommonResult findAllReturn() {
        List<ReturnGoods> returnGoods = returnGoodsService.selectAll();
        if (returnGoods != null) {
            return CommonResult.success("退货商品查询成功", returnGoods);
        } else {
            return CommonResult.error("退货商品查询失败");
        }
    }

    @RequestMapping(value = "/returnGoods/findCount")
    public CommonResult findCount() {
        Integer count = returnGoodsService.selectCount();
        if (count != null) {
            return CommonResult.success("退货订单数量查询成功", count);
        } else {
            return CommonResult.error("退货订单数量查询失败");
        }
    }

    @RequestMapping(value = "/returnGoods/addReturn")
    public CommonResult addReturn(ReturnGoods returnGoods) {
        if (returnGoods != null) {
            Integer orderId = orderService.selectIdByKey(returnGoods.getOrderNo());
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState("待处理");
            if (orderService.updateById(order)) {
                if (returnGoodsService.insertData(returnGoods)) {
                    return CommonResult.success("添加成功", returnGoods);
                } else {
                    return CommonResult.error("添加失败");
                }
            } else {
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("商品退货数据不存在");
    }

    @RequestMapping(value = "/returnGoods/updateReturn")
    public CommonResult updateReturn(ReturnGoods returnGoods) {
        if (returnGoods != null) {
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /*我的订单 查询退货订单信息*/
    @RequestMapping(value = "/returnGoods/findReturnInfo")
    public CommonResult findReturnInfo(String userNumber) {
        if (userNumber != null) {
            List<Map<String, Object>> data = returnGoodsService.selectAllOrder(userNumber);
            return CommonResult.success("商品退货订单查询成功", data);
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /*拒绝买家退货*/
    @RequestMapping(value = "/returnGoods/refuseReturn")
    public CommonResult refuseReturn(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState("已拒绝");
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState("已拒绝");
            if (orderService.updateById(order)) {
                if (returnGoodsService.updateById(returnGoods)) {
                    return CommonResult.success("更新成功", returnGoods);
                } else {
                    return CommonResult.error("更新失败");
                }
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /*同意买家退货*/
    @RequestMapping(value = "/returnGoods/dealRefund")
    public CommonResult dealRefund(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState("退款完成");
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setReturnState(true);
            order.setOrderState("已退款");
            Logistics logistics = logisticsService.selectOrderNo(orderNo);
            if (logistics != null) {
                logisticsService.deleteById(logistics.getLogisticId());
            }
            if (orderService.updateById(order)) {
                if (returnGoodsService.updateById(returnGoods)) {
                    return CommonResult.success("更新成功", returnGoods);
                } else {
                    return CommonResult.error("更新失败");
                }
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退款数据不存在");
    }

    /*拒绝买家退款申请*/
    @RequestMapping(value = "/returnGoods/rejectRefund")
    public CommonResult rejectRefund(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState("拒绝退款");
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState("待发货");
            if (orderService.updateById(order)) {
                if (returnGoodsService.updateById(returnGoods)) {
                    return CommonResult.success("更新成功", returnGoods);
                } else {
                    return CommonResult.error("更新失败");
                }
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退款数据不存在");
    }


    /*同意买家退货*/
    @RequestMapping(value = "/returnGoods/dealWithReturn")
    public CommonResult dealWithReturn(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState("允许退货");
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setReturnState(true);
            order.setOrderState("已退货");
            Logistics logistics = logisticsService.selectOrderNo(orderNo);
            if (logistics != null) {
                logisticsService.deleteById(logistics.getLogisticId());
            }
            if (orderService.updateById(order)) {
                if (returnGoodsService.updateById(returnGoods)) {
                    return CommonResult.success("更新成功", returnGoods);
                } else {
                    return CommonResult.error("更新失败");
                }
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退货数据不存在");
    }

    /*买家快递寄回*/
    @RequestMapping(value = "/returnGoods/sendBack")
    public CommonResult sendBack(Integer returnId) {
        if (returnId != null) {
            ReturnGoods returnGoods = new ReturnGoods();
            returnGoods.setReturnId(returnId);
            returnGoods.setReturnState("待收货");
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退货数据不完整");
    }

    /*商家收到寄回的商品*/
    @RequestMapping(value = "/returnGoods/receipt")
    public CommonResult receipt(Integer returnId) {
        if (returnId != null) {
            ReturnGoods returnGoods = new ReturnGoods();
            returnGoods.setReturnId(returnId);
            returnGoods.setReturnState("退货完成");
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            } else {
                return CommonResult.error("更新失败");
            }
        }
        return CommonResult.error("商品退货数据不完整");
    }

    @RequestMapping(value = "/returnGoods/deleteReturn")
    public CommonResult deleteReturn(Integer returnId) {
        if (returnId != null) {
            if (returnGoodsService.deleteById(returnId)) {
                return CommonResult.success("删除成功", returnId);
            } else {
                return CommonResult.error("删除失败");
            }
        }
        return CommonResult.error("商品退货数据不存在");
    }

    /*查询物流信息*/
    @RequestMapping(value = "/logistics/findInfoById")
    public CommonResult findInfoById(Integer logisticId) {
        Logistics logistics = logisticsService.selectById(logisticId);
        if (logistics != null) {
            return CommonResult.success("物流信息查询成功", logistics);
        } else {
            return CommonResult.error("物流信息查询失败");
        }
    }

    /*查询全部物流信息*/
    @RequestMapping(value = "/logistics/findAllInfo")
    public CommonResult findAllInfo() {
        List<Logistics> logistics = logisticsService.selectAll();
        if (logistics != null) {
            return CommonResult.success("物流信息查询成功", logistics);
        } else {
            return CommonResult.error("物流信息查询失败");
        }
    }


    @RequestMapping(value = "/logistics/addInfo")
    public CommonResult addInfo(Logistics logistics) {
        if (logistics != null) {
            if (logisticsService.insertData(logistics)) {
                return CommonResult.success("物流信息添加成功", logistics);
            } else {
                return CommonResult.error("物流信息添加失败");
            }
        }
        return CommonResult.error("物流信息数据不存在");
    }


    @RequestMapping(value = "/logistics/deleteInfo")
    public CommonResult deleteInfo(Integer logisticId) {
        if (logisticId != null) {
            if (logisticsService.deleteById(logisticId)) {
                return CommonResult.success("物流信息删除成功", logisticId);
            } else {
                return CommonResult.error("物流信息删除失败");
            }
        }
        return CommonResult.error("物流信息数据不存在");
    }

    @RequestMapping(value = "/orderDetail/returnInfo")
    public CommonResult returnInfo(String orderNo) {
        ArrayList<Object> resultList = new ArrayList<>();
        Map<String, Object> returnGoods = returnGoodsService.selectOrderNo(orderNo);
        Logistics logistics = logisticsService.selectOrderNo(orderNo);
        if (returnGoods != null) {
            resultList.add(returnGoods);
        }
        if (logistics != null) {
            resultList.add(logistics);
        }

        if (resultList.size() != 0) {
            return CommonResult.success("退货订单详情查询成功", resultList);
        } else {
            return CommonResult.error("退货订单详情查询失败");
        }
    }

}
