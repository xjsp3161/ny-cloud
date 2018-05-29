package com.nycloud.admin.controller;

import com.nycloud.admin.annotation.PreAuth;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
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

    private final String URL = "api/sysUser";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @ApiOperation(value = "用户列表查询", notes = "可分页并可根据用户名称模糊检索")
    @GetMapping
    @PreAuth("hasAuthority('GET_SYS_USER_PAGE_LIST')")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("username");
        return new HttpResponse().success(sysUserService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户详情查询", notes = "根据id查询用户详细信息")
    @GetMapping("/info")
    @PreAuth("hasAuthority('GET_SYS_USER_PAGE_LIST')")
    public HttpResponse info(@RequestParam Long id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return new HttpResponse().success(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "用户可用菜单树查询", notes = "根据用户权限查询已分配好的菜单")
    @GetMapping("/userMenuTree")
    public HttpResponse userMenuTree() {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionMenuTree(1));
    }

    @ApiOperation(value = "用户所有可用资源查询", notes = "根据用户Id查询分配的角色权限下面的资源列表")
    @GetMapping("/userResources")
    public HttpResponse userResources(Long userId) {
        return new HttpResponse().success(sysUserService.selectUserResources(userId));
    }

}
