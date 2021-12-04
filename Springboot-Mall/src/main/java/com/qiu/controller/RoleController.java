package com.qiu.controller;

import com.qiu.entity.Role;
import com.qiu.service.RoleService;
//import com.qiu.util.general.CommonResult;
import com.qiu.util.general.CommonResult;
//import com.power.common.model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:44
 * @description 用户授权等相关业务
 */

@RestController
@CrossOrigin
public class RoleController {
    final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /*根据id查询用户*/
    @RequestMapping(value = "/role/findById")
    private CommonResult findById(Integer roleId) {
        Role role = roleService.selectById(roleId);
        if(role!=null){
            return CommonResult.success("查询成功",role);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    /*根据角色名称查询角色*/
    @RequestMapping(value = "/role/findByKey")
    private CommonResult findByKey(String roleName) {
        Role role = roleService.selectByKey(roleName);
        if(role!=null){
            return  CommonResult.success("查询成功",role);
        }else{
            return CommonResult.error("查询失败");
        }
    }
    /*根据key查询用户*/
    @RequestMapping(value = "/role/existRoleName")
    private CommonResult existRoleName(Integer roleId,String roleName) {
        Boolean isExist = roleService.existsRoleName(roleId,roleName);
        if(isExist!=null){
            return  CommonResult.success("查询成功",isExist);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    /*查询所有角色信息*/
    @RequestMapping(value = "/role/findAll")
    private CommonResult findAll() {
        List<Role> roles = roleService.selectAll();
        if(roles!=null){
            return CommonResult.success("查询成功",roles);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    /*查询所有用户*/
    @RequestMapping(value = "/role/findAllUsable")
    private CommonResult findAllUsable() {
        List<Role> roles = roleService.selectAllUsable();
        if(roles!=null){
            return CommonResult.success("查询成功",roles);
        }else{
            return CommonResult.error("查询失败");
        }
    }


    @RequestMapping(value = "/role/count")
    private CommonResult findCount() {
        Integer count = roleService.selectCount();
        if(count!=null){
            return CommonResult.success("查询成功",count);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/role/findIdByKey")
    private CommonResult findIdByKey(String key) {
        Integer id = roleService.selectIdByKey(key);
        if(id!=null){
            return CommonResult.success("查询成功","id: "+id);
        }else{
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/role/add")
    private CommonResult add(Role role) {
        if(role!=null){
            if(roleService.insertData(role)){
                return CommonResult.success("添加成功",role);
            }else{
                return CommonResult.error("添加失败");
            }
        }
        return CommonResult.error("用户数据不存在");
    }

    @RequestMapping(value = "/role/update")
    private CommonResult update(Role role) {
        System.out.println(role);
        if(roleService.updateById(role)){
            return CommonResult.success("更新成功",role);
        }else{
            return CommonResult.error("更新失败");
        }
    }

    @RequestMapping(value = "/role/delete")
    private CommonResult delete(Integer roleId) {
        if(roleService.deleteById(roleId)){
            return CommonResult.success("删除成功",roleId);
        }else{
            return CommonResult.error("删除失败");
        }
    }
}
