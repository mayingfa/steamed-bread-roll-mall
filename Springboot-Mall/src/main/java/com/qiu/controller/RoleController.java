package com.qiu.controller;

import com.qiu.entity.Role;
import com.qiu.service.RoleService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:44
 * @description 用户授权等相关业务
 */
@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 根据id查询角色信息
     *
     * @param roleId 角色编号
     */
    @RequestMapping(value = "/role/findById")
    public CommonResult findById(Integer roleId) {
        Role role = roleService.selectById(roleId);
        if (role != null) {
            return CommonResult.success("查询成功", role);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 根据角色名称查询角色
     *
     * @param roleName 角色名称
     */
    @RequestMapping(value = "/role/findByKey")
    public CommonResult findByKey(String roleName) {
        Role role = roleService.selectByKey(roleName);
        if (role != null) {
            return CommonResult.success("查询成功", role);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 判断角色是否存在
     *
     * @param roleId   角色编号
     * @param roleName 角色名称
     */
    @RequestMapping(value = "/role/existRoleName")
    public CommonResult existRoleName(Integer roleId, String roleName) {
        boolean exist = roleService.existsRoleName(roleId, roleName);
        return CommonResult.success("查询成功", exist);
    }

    /**
     * 查询所有角色信息
     */
    @RequestMapping(value = "/role/findAll")
    public CommonResult findAll() {
        List<Role> roles = roleService.selectAll();
        if (roles != null) {
            return CommonResult.success("查询成功", roles);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询所有可用的角色信息
     */
    @RequestMapping(value = "/role/findAllUsable")
    public CommonResult findAllUsable() {
        List<Role> roles = roleService.selectAllUsable();
        if (roles != null) {
            return CommonResult.success("查询成功", roles);
        }
        return CommonResult.error("查询失败");
    }

    /**
     * 查询角色数量
     */
    @RequestMapping(value = "/role/count")
    public CommonResult findCount() {
        int count = roleService.selectCount();
        return CommonResult.success("查询成功", count);
    }

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     */
    @RequestMapping(value = "/role/add")
    public CommonResult add(Role role) {
        if (role != null) {
            if (roleService.insertData(role)) {
                return CommonResult.success("添加成功", role);
            }
            return CommonResult.error("添加失败");
        }
        return CommonResult.error("角色信息不存在");
    }

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     */
    @RequestMapping(value = "/role/update")
    public CommonResult update(Role role) {
        if (roleService.updateById(role)) {
            return CommonResult.success("更新成功", role);
        }
        return CommonResult.error("更新失败");
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色编号
     */
    @RequestMapping(value = "/role/delete")
    public CommonResult delete(Integer roleId) {
        if (roleService.deleteById(roleId)) {
            return CommonResult.success("删除成功", roleId);
        }
        return CommonResult.error("删除失败");
    }
}
