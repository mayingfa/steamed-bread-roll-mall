package com.qiu.controller;

import com.qiu.entity.UserRole;
import com.qiu.service.UserRoleService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:52
 * @description 用户授权类
 */

@RestController
@CrossOrigin
public class UserRoleController {

    final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /*根据id查询用户*/
    @RequestMapping(value = "/userRole/findById")
    public CommonResult findById(Integer userId) {
        List<UserRole> userRoles = userRoleService.selectByUserId(userId);
        if (userRoles != null) {
            return CommonResult.success("查询成功", userRoles);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/userRole/findRoleById")
    public CommonResult findRoleById(Integer userId) {
        List<Map<String, Object>> maps = userRoleService.selectRoleByUserId(userId);
        if (maps != null) {
            return CommonResult.success("查询成功", maps);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    /*查询所有用户*/
    @RequestMapping(value = "/userRole/findAll")
    public CommonResult findAll() {
        List<UserRole> userRoles = userRoleService.selectAll();
        if (userRoles != null) {
            return CommonResult.success("查询成功", userRoles);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    /*查询所有用户*/
    @RequestMapping(value = "/userRole/existsRole")
    public CommonResult existsRole(Integer roleId) {
        Boolean isExist = userRoleService.existsRole(roleId);
        if (isExist != null) {
            return CommonResult.success("查询成功", isExist);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/userRole/add")
    public CommonResult add(UserRole userRole) {
        if (userRole != null) {
            if (userRoleService.insertData(userRole)) {
                return CommonResult.success("添加成功", userRole);
            } else {
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("用户数据不存在");
    }

    @RequestMapping(value = "/userRole/delete")
    public CommonResult delete(Integer userId) {
        if (userRoleService.deleteById(userId)) {
            return CommonResult.success("删除成功", userId);
        } else {
            return CommonResult.error("删除失败");
        }
    }


}
