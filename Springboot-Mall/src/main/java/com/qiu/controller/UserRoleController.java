package com.qiu.controller;

import com.qiu.entity.UserRole;
import com.qiu.service.UserRoleService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:52
 * @description 用户授权类
 */
@CrossOrigin
@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据id查询用户
     *
     * @param userId 用户编号
     */
    @RequestMapping(value = "/userRole/findById")
    public CommonResult findById(Integer userId) {
        List<UserRole> userRoles = userRoleService.selectByUserId(userId);
        if (userRoles != null) {
            return CommonResult.success("查询成功", userRoles);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 根据用户Id查询角色信息
     *
     * @param userId 用户编号
     */
    @RequestMapping(value = "/userRole/findRoleById")
    public CommonResult findRoleById(Integer userId) {
        return CommonResult.success("查询成功", userRoleService.selectRoleByUserId(userId));
    }


    /**
     * 判断角色是否存在
     *
     * @param roleId 角色编号
     */
    @RequestMapping(value = "/userRole/existsRole")
    public CommonResult existsRole(Integer roleId) {
        boolean exist = userRoleService.existsRole(roleId);
        return CommonResult.success("查询成功", exist);
    }

    @RequestMapping(value = "/userRole/add")
    public CommonResult add(UserRole userRole) {
        if (userRole != null) {
            if (userRoleService.insertData(userRole)) {
                return CommonResult.success("添加成功", userRole);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("用户数据不存在");
    }

    @RequestMapping(value = "/userRole/delete")
    public CommonResult delete(Integer userId) {
        if (userRoleService.deleteById(userId)) {
            return CommonResult.success("删除成功", userId);
        }
        return CommonResult.error("删除失败");
    }
}
