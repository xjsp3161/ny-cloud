package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.security.UserEntity;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
public class SysUserController {

    private final String URL_MAPPING = "api/sysUser";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @ApiOperation(value = "用户列表查询", notes = "可分页并可根据用户名称模糊检索")
    @GetMapping(URL_MAPPING)
    @PreAuth("hasAuthority('sys_user_get')")
    @ResourcesMapping(elements = "查询", code = "sys_user_get")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("username");
        return new HttpResponse().success(sysUserService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户详情查询", notes = "根据id查询用户详细信息")
    @GetMapping(URL_MAPPING + "/info")
    @PreAuth("hasAuthority('"+ URL_MAPPING +"/info')")
    @ResourcesMapping(elements = "查询详情", code = "sys_user_get_info")
    public HttpResponse info(@RequestParam Long id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return new HttpResponse().success(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "获取登陆授权后的用户信息", notes = "根据授权Authentication中UserEntity中的userId获取")
    @GetMapping(URL_MAPPING + "/userInfo")
    @ResourcesMapping(elements = "查询详情", code = "sys_user_get_user_info")
    public HttpResponse userInfo(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setId(userEntity.getUserId());
        return new HttpResponse().success(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "用户可用菜单树查询", notes = "根据用户权限查询已分配好的菜单")
    @GetMapping(URL_MAPPING + "/userMenuTree")
    public HttpResponse userMenuTree() {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionMenuTree(1));
    }

    @ApiOperation(value = "用户所有可用资源查询", notes = "根据用户Id查询分配的角色权限下面的资源列表")
    @GetMapping("public/" + URL_MAPPING + "/userResources")
    public HttpResponse userResources(Long userId) {
        return new HttpResponse().success(sysUserService.selectUserResources(userId));
    }

}
