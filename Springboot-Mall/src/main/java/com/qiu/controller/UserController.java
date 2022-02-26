package com.qiu.controller;

import com.qiu.entity.User;
import com.qiu.entity.UserRole;
import com.qiu.entity.Vip;
import com.qiu.service.UserRoleService;
import com.qiu.service.UserService;
import com.qiu.service.VipService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/28 18:11
 * @description 用户相关业务
 */
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private VipService vipService;

    /**
     * 根据id查询用户
     *
     * @param id 用户编号
     */
    @RequestMapping(value = "/user/findById")
    public CommonResult findById(Integer id) {
        User user = userService.selectById(id);
        if (user != null) {
            return CommonResult.success("查询成功", user);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    /**
     * 根据账号查询用户
     *
     * @param key 账号
     */
    @RequestMapping(value = "/user/findByKey")
    public CommonResult findByKey(String key) {
        User user = userService.selectByKey(key);
        if (user != null) {
            return CommonResult.success("查询成功", user);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/user/findAll")
    public CommonResult findAll() {
        List<User> users = userService.selectAll();
        if (users != null) {
            return CommonResult.success("查询成功", users);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 判断某个用户是否还存在
     *
     * @param key 账号
     */
    @RequestMapping(value = "/user/existKey")
    public CommonResult existKey(String key) {
        boolean exist = userService.existsWithPrimaryKey(key);
        return CommonResult.success("查询成功", exist);
    }

    /**
     * 查询用户状态
     *
     * @param accountNumber 用户账号
     */
    @RequestMapping(value = "/user/userState")
    public CommonResult userState(String accountNumber) {
        boolean state = userService.selectUserState(accountNumber);
        return CommonResult.success("查询成功", state);
    }

    /**
     * 查询用户记录的总个数
     */
    @RequestMapping(value = "/user/count")
    public CommonResult findCount() {
        int count = userService.selectCount();
        return CommonResult.success("查询成功", count);

    }

    /**
     * 通过用户账号查询用户ID
     *
     * @param key 用户账号
     */
    @RequestMapping(value = "/user/findIdByKey")
    public CommonResult findIdByKey(String key) {
        Integer id = userService.selectIdByKey(key);
        if (id != null) {
            return CommonResult.success("查询成功", id);
        }
        return CommonResult.error("未查询到");
    }

    /**
     * 删除用户
     *
     * @param userId 用户编号
     */
    @RequestMapping(value = "/user/delete")
    public CommonResult delete(Integer userId) {
        if (userService.deleteById(userId)) {
            return CommonResult.success("删除成功", userId);
        }
        return CommonResult.error("删除失败");
    }

    /**
     * 角色授权
     *
     * @param userId 用户编号
     * @param roleId 角色编号列表
     */
    @RequestMapping(value = "/user/author")
    public CommonResult author(Integer userId, @RequestParam List<Integer> roleId) {
        if (userId != null && roleId != null && !roleId.isEmpty()) {
            if (userRoleService.deleteById(userId)) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                for (Integer id : roleId) {
                    userRole.setRoleId(id);
                    userRoleService.insertData(userRole);
                }
            }
            return CommonResult.success("授权成功");
        } else {
            return CommonResult.error("角色授权数据不完整！");
        }
    }

    /**
     * 查询所有VIP用户
     */
    @RequestMapping(value = "/vip/findAllVip")
    public CommonResult findAllVip() {
        List<Vip> vips = vipService.selectAll();
        if (vips != null) {
            return CommonResult.success("查询成功", vips);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询VIP用户信息根据id
     *
     * @param vipId 会员编号
     */
    @RequestMapping(value = "/vip/findVipById")
    public CommonResult findVipById(Integer vipId) {
        Vip vip = vipService.selectById(vipId);
        if (vip != null) {
            return CommonResult.success("查询成功", vip);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询VIP用户信息根据id
     *
     * @param accountNumber 用户账号
     */
    @RequestMapping(value = "/vip/findVipByKey")
    public CommonResult findVipByKey(String accountNumber) {
        Vip vip = vipService.selectByKey(accountNumber);
        if (vip != null) {
            return CommonResult.success("查询成功", vip);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 判断用户信息是否存在
     *
     * @param accountNumber 用户账号
     */
    @RequestMapping(value = "/vip/existsVip")
    public CommonResult existsVip(String accountNumber) {
        boolean exist = vipService.existsVip(accountNumber);
        return CommonResult.success("查询成功", exist);
    }

    /**
     * 增加会员信息
     *
     * @param vip 会员信息
     */
    @RequestMapping(value = "/vip/addVip")
    public CommonResult addVip(Vip vip) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        //设置起时间
        cal.setTime(date);
        //增加一年
        cal.add(Calendar.YEAR, 1);
        vip.setOverdueTime(cal.getTime());
        if (vipService.insertData(vip)) {
            return CommonResult.success("会员信息添加成功", vip);
        }
        return CommonResult.error("会员信息添加失败");
    }

    /**
     * 更新会员信息
     *
     * @param vip 会员信息
     */
    @RequestMapping(value = "/vip/updateVip")
    public CommonResult updateVip(Vip vip) {
        if (vipService.updateById(vip)) {
            return CommonResult.success("会员信息更新成功", vip);
        }
        return CommonResult.error("会员信息更新失败");
    }

    /**
     * 清除信息
     *
     * @param vipId 会员编号
     */
    @RequestMapping(value = "/vip/deleteVip")
    public CommonResult deleteVip(Integer vipId) {
        if (vipService.deleteById(vipId)) {
            return CommonResult.success("删除成功", vipId);
        }
        return CommonResult.error("删除失败");
    }
}
