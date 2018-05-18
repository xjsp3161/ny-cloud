package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.service.SysMenuService;
import com.nycloud.admin.service.SysPermissionMenuPkService;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionMenuPkService sysPermissionMenuPkService;

    @ApiOperation(value = "用户列表查询", notes = "可分页并可根据用户名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("username");
        return new HttpResponse().success(sysUserService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户详情查询", notes = "根据id查询用户详细信息")
    @GetMapping("/info")
    public HttpResponse info(@RequestParam Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return new HttpResponse().success(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "用户可用菜单树查询", notes = "根据用户权限查询已分配好的菜单")
    @GetMapping("/userMenuTree")
    public HttpResponse userMenuTree() {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionMenuTree(1));
    }

}
