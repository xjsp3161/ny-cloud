package com.nycloud.admin.controller;

import com.nycloud.admin.dto.PermissionMenuDto;
import com.nycloud.admin.model.SysPermissionMenuPk;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@Api(value = "权限关联菜单", tags = {"权限管理接口"})
@RestController
@RequestMapping(value = "api/sysPermissionMenu")
public class SysPermissionMenuController {

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @ApiOperation(value = "权限关联菜单批量添加",  notes = "保存多个SysPermissionMenuPk对象")
    @ResourcesMapping(element = "添加", code = "sys_permission_menu_add")
    @PreAuth("hasAuthority('sys_permission_menu_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysPermissionMenuPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限关联菜单批量删除",  notes = "根据权限id和多个菜单id删除关联")
    @ResourcesMapping(element = "删除", code = "sys_permission_menu_delete")
    @PreAuth("hasAuthority('sys_permission_menu_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody PermissionMenuDto dto) {
        if (dto.getMenuIds() == null || dto.getMenuIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchDelete(dto.getPermissionId(), dto.getMenuIds());
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限未关联菜单树查询", notes = "根据权限id查询该权限未关联的菜单并返回菜单树")
    @ResourcesMapping(element = "查询", code = "sys_permission_no_menu_tree_get")
    @PreAuth("hasAuthority('sys_permission_no_menu_tree_get')")
    @GetMapping("/permissionNoRelationMenuTree")
    public HttpResponse permissionNoRelationMenuTree(@RequestParam Integer permissionId) {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionNoMenuTree(permissionId));
    }

    @ApiOperation(value = "权限已关联菜单树查询",  notes = "根据权限id查询该权限已关联的菜单并返回菜单树")
    @ResourcesMapping(element = "查询", code = "sys_permission_menu_tree_get")
    @PreAuth("hasAuthority('sys_permission_menu_tree_get')")
    @GetMapping("/permissionMenuTree")
    public HttpResponse permissionMenuTree(@RequestParam Integer permissionId) {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionMenuTree(permissionId));
    }

}
