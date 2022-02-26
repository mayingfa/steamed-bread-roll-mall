package com.qiu.controller;

import com.qiu.entity.Logistics;
import com.qiu.entity.Order;
import com.qiu.entity.ReturnGoods;
import com.qiu.entity.ReturnReason;
import com.qiu.service.LogisticsService;
import com.qiu.service.OrderService;
import com.qiu.service.ReturnGoodsService;
import com.qiu.service.ReturnReasonService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 21:09
 * @description 商品退回
 */
@CrossOrigin
@RestController
public class ReturnProductController {
    private static final String WAITING_PROCESS = "待处理";
    private static final String REFUSED = "已拒绝";
    private static final String REFUND_COMPLETE = "退款完成";
    private static final String REFUNDED = "已退款";
    private static final String REFUSED_REFUND = "拒绝退款";
    private static final String WAIT_SEND = "待发货";
    private static final String ALLOW_RETURN = "允许退货";
    private static final String RETURNED = "已退货";


    @Autowired
    private ReturnReasonService returnReasonService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    private OrderService orderService;

    /**
     * 退货原因
     *
     * @param reasonId 退货原因编号
     */
    @RequestMapping(value = "/returnReason/findReasonById")
    public CommonResult findReasonById(Integer reasonId) {
        ReturnReason returnReason = returnReasonService.selectById(reasonId);
        if (returnReason != null) {
            return CommonResult.success("退货原因查询成功", returnReason);
        }
        return CommonResult.error("退货原因查询失败");
    }

    /**
     * 查询全部退货原因
     */
    @RequestMapping(value = "/returnReason/findAllReason")
    public CommonResult findAllReason() {
        List<ReturnReason> returnReasons = returnReasonService.selectAll();
        if (returnReasons != null) {
            return CommonResult.success("退货原因查询成功", returnReasons);
        }
        return CommonResult.error("退货原因查询失败");
    }

    /**
     * 查询全部退货原因名称
     */
    @RequestMapping(value = "/returnReason/findReasonName")
    public CommonResult findReasonName() {
        List<String> names = returnReasonService.selectAllName();
        if (names != null) {
            return CommonResult.success("退货原因查询成功", names);
        }
        return CommonResult.error("退货原因查询失败");
    }

    /**
     * 查询退货原因是否存在
     *
     * @param reasonId   退货原因编号
     * @param reasonName 退货原因
     */
    @RequestMapping(value = "/returnReason/existReasonName")
    public CommonResult existReasonName(Integer reasonId, String reasonName) {
        Boolean isExist = returnReasonService.existsWithReasonName(reasonId, reasonName);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 添加退货原因
     *
     * @param returnReason 退货原因
     */
    @RequestMapping(value = "/returnReason/addReason")
    public CommonResult addReason(ReturnReason returnReason) {
        if (returnReason != null) {
            if (returnReasonService.insertData(returnReason)) {
                return CommonResult.success("添加成功", returnReason);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("退货原因数据不存在");
    }


    /**
     * 更新退货原因
     *
     * @param returnReason 退货原因
     */
    @RequestMapping(value = "/returnReason/updateReason")
    public CommonResult updateReason(ReturnReason returnReason) {
        if (returnReason != null) {
            if (returnReasonService.updateById(returnReason)) {
                return CommonResult.success("更新成功", returnReason);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("退货原因数据不存在");
    }

    /**
     * 删除退货原因
     *
     * @param reasonId 退货原因编号
     */
    @RequestMapping(value = "/returnReason/deleteReason")
    public CommonResult updateReason(Integer reasonId) {
        if (reasonId != null) {
            if (returnReasonService.deleteById(reasonId)) {
                return CommonResult.success("删除成功", reasonId);
            }
            return CommonResult.error("删除失败");
        }
        return CommonResult.error("退货原因数据不存在");
    }

    /**
     * 查询商品退货信息
     *
     * @param returnId 退货商品Id
     */
    @RequestMapping(value = "/returnGoods/findReturnById")
    public CommonResult findReturnById(Integer returnId) {
        ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
        if (returnGoods != null) {
            return CommonResult.success("退货商品查询成功", returnGoods);
        }
        return CommonResult.error("退货商品查询失败");
    }

    /**
     * 查询全部退货商品
     */
    @RequestMapping(value = "/returnGoods/findAllReturn")
    public CommonResult findAllReturn() {
        List<ReturnGoods> returnGoods = returnGoodsService.selectAll();
        if (returnGoods != null) {
            return CommonResult.success("退货商品查询成功", returnGoods);
        }
        return CommonResult.error("退货商品查询失败");
    }

    @RequestMapping(value = "/returnGoods/findCount")
    public CommonResult findCount() {
        int count = returnGoodsService.selectCount();
        return CommonResult.success("退货订单数量查询成功", count);
    }

    /**
     * 添加退货商品记录
     *
     * @param returnGoods 退货商品信息
     */
    @RequestMapping(value = "/returnGoods/addReturn")
    public CommonResult addReturn(ReturnGoods returnGoods) {
        if (returnGoods != null) {
            Integer orderId = orderService.selectIdByKey(returnGoods.getOrderNo());
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState(WAITING_PROCESS);
            if (orderService.updateById(order) && returnGoodsService.insertData(returnGoods)) {
                return CommonResult.success("添加成功", returnGoods);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("商品退货数据不存在");
    }

    /**
     * 更新退货商品
     *
     * @param returnGoods 商品信息
     */
    @RequestMapping(value = "/returnGoods/updateReturn")
    public CommonResult updateReturn(ReturnGoods returnGoods) {
        if (returnGoods != null) {
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /**
     * 我的订单 查询退货订单信息
     *
     * @param userNumber 用户账号
     */
    @RequestMapping(value = "/returnGoods/findReturnInfo")
    public CommonResult findReturnInfo(String userNumber) {
        if (userNumber != null) {
            List<Map<String, Object>> data = returnGoodsService.selectAllOrder(userNumber);
            return CommonResult.success("商品退货订单查询成功", data);
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /**
     * 拒绝买家退货
     *
     * @param returnId       退货编号
     * @param operatorNumber 操作人账号
     * @param operatorName   操作人姓名
     */
    @RequestMapping(value = "/returnGoods/refuseReturn")
    public CommonResult refuseReturn(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState(REFUSED);
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState(REFUSED);
            if (orderService.updateById(order)) {
                if (returnGoodsService.updateById(returnGoods)) {
                    return CommonResult.success("更新成功", returnGoods);
                }
                return CommonResult.error("更新失败");
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退货数据不存在");
    }


    /**
     * 同意买家退货
     *
     * @param returnId       退货编号
     * @param operatorNumber 操作人账号
     * @param operatorName   操作人姓名
     */
    @RequestMapping(value = "/returnGoods/dealRefund")
    public CommonResult dealRefund(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState(REFUND_COMPLETE);
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setReturnState(true);
            order.setOrderState(REFUNDED);
            Logistics logistics = logisticsService.selectOrderNo(orderNo);
            if (logistics != null) {
                logisticsService.deleteById(logistics.getLogisticId());
            }
            if (orderService.updateById(order) && returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退款数据不存在");
    }

    /**
     * 拒绝买家退款申请
     *
     * @param returnId       退货编号
     * @param operatorNumber 操作人账号
     * @param operatorName   操作人姓名
     */
    @RequestMapping(value = "/returnGoods/rejectRefund")
    public CommonResult rejectRefund(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState(REFUSED_REFUND);
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState(WAIT_SEND);
            if (orderService.updateById(order) && returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退款数据不存在");
    }


    /**
     * 同意买家退货
     *
     * @param returnId       退货编号
     * @param operatorNumber 操作人账号
     * @param operatorName   操作人姓名
     */
    @RequestMapping(value = "/returnGoods/dealWithReturn")
    public CommonResult dealWithReturn(Integer returnId, String operatorNumber, String operatorName) {
        if (returnId != null) {
            ReturnGoods returnGoods = returnGoodsService.selectById(returnId);
            returnGoods.setReturnState(ALLOW_RETURN);
            returnGoods.setDealTime(new Date());
            returnGoods.setOperatorNumber(operatorNumber);
            returnGoods.setOperatorName(operatorName);
            String orderNo = returnGoods.getOrderNo();
            Integer orderId = orderService.selectIdByKey(orderNo);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setReturnState(true);
            order.setOrderState(RETURNED);
            Logistics logistics = logisticsService.selectOrderNo(orderNo);
            if (logistics != null) {
                logisticsService.deleteById(logistics.getLogisticId());
            }
            if (orderService.updateById(order) && returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退货数据不存在");
    }

    /**
     * 买家快递寄回
     *
     * @param returnId 退货编号
     */
    @RequestMapping(value = "/returnGoods/sendBack")
    public CommonResult sendBack(Integer returnId) {
        if (returnId != null) {
            ReturnGoods returnGoods = new ReturnGoods();
            returnGoods.setReturnId(returnId);
            returnGoods.setReturnState("待收货");
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退货数据不完整");
    }

    /**
     * 商家收到寄回的商品
     *
     * @param returnId 退货编号
     */
    @RequestMapping(value = "/returnGoods/receipt")
    public CommonResult receipt(Integer returnId) {
        if (returnId != null) {
            ReturnGoods returnGoods = new ReturnGoods();
            returnGoods.setReturnId(returnId);
            returnGoods.setReturnState("退货完成");
            returnGoods.setDealTime(new Date());
            if (returnGoodsService.updateById(returnGoods)) {
                return CommonResult.success("更新成功", returnGoods);
            }
            return CommonResult.error("更新失败");
        }
        return CommonResult.error("商品退货数据不完整");
    }

    @RequestMapping(value = "/returnGoods/deleteReturn")
    public CommonResult deleteReturn(Integer returnId) {
        if (returnId != null) {
            if (returnGoodsService.deleteById(returnId)) {
                return CommonResult.success("删除成功", returnId);
            }
            return CommonResult.error("删除失败");
        }
        return CommonResult.error("商品退货数据不存在");
    }

    /**
     * 查询物流信息
     *
     * @param logisticId 物流编号
     */
    @RequestMapping(value = "/logistics/findInfoById")
    public CommonResult findInfoById(Integer logisticId) {
        Logistics logistics = logisticsService.selectById(logisticId);
        if (logistics != null) {
            return CommonResult.success("物流信息查询成功", logistics);
        }
        return CommonResult.error("物流信息查询失败");
    }

    /**
     * 查询全部物流信息
     */
    @RequestMapping(value = "/logistics/findAllInfo")
    public CommonResult findAllInfo() {
        List<Logistics> logistics = logisticsService.selectAll();
        if (logistics != null) {
            return CommonResult.success("物流信息查询成功", logistics);
        }
        return CommonResult.error("物流信息查询失败");
    }


    @RequestMapping(value = "/logistics/addInfo")
    public CommonResult addInfo(Logistics logistics) {
        if (logistics != null) {
            if (logisticsService.insertData(logistics)) {
                return CommonResult.success("物流信息添加成功", logistics);
            }
            return CommonResult.error("物流信息添加失败");
        }
        return CommonResult.error("物流信息数据不存在");
    }


    @RequestMapping(value = "/logistics/deleteInfo")
    public CommonResult deleteInfo(Integer logisticId) {
        if (logisticId != null) {
            if (logisticsService.deleteById(logisticId)) {
                return CommonResult.success("物流信息删除成功", logisticId);
            }
            return CommonResult.error("物流信息删除失败");
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
        return CommonResult.success("退货订单详情查询成功", resultList);
    }
}
