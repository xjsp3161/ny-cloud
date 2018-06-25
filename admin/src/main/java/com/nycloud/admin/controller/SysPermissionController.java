package com.nycloud.admin.controller;

import com.nycloud.admin.dto.PermissionMenuDto;
import com.nycloud.admin.dto.PermissionResourceDto;
import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.model.SysPermissionMenuPk;
import com.nycloud.admin.model.SysPermissionResourcePk;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
import com.nycloud.admin.service.SysPermissionResourceServicePk;
import com.nycloud.admin.service.SysPermissionService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@Api(value = "权限", tags = {"权限管理接口"})
@RestController
@RequestMapping(value = "api/sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @Autowired
    private SysPermissionResourceServicePk sysPermissionResourceServicePk;

    @ApiOperation(value = "权限添加", notes = "根据SysPermission对象创建权限")
    @ResourcesMapping(element = "添加", code = "sys_permission_add")
    @PreAuth("hasAuthority('sys_permission_add')")
    @PostMapping
    public HttpResponse add(@RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.insert(sysPermission);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限删除", notes = "根据权限id删除权限信息")
    @ResourcesMapping(element = "删除", code = "sys_permission_delete")
    @PreAuth("hasAuthority('sys_permission_delete')")
    @DeleteMapping("/{id}")
    public HttpResponse delete(@PathVariable int id) {
        sysPermissionService.delete(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限查询", notes = "可分页并可根据权限名称模糊检索")
    @ResourcesMapping(element = "查询", code = "sys_permission_query")
    @PreAuth("hasAuthority('sys_permission_query')")
    @GetMapping
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("name");
        return HttpResponse.resultSuccess(sysPermissionService.findByPageList(requestDto));
    }

    @ApiOperation(value = "权限修改", notes = "根据传递的SysPermission对象来更新, SysPermission对象必须包含id")
    @ResourcesMapping(element = "修改", code = "sys_permission_update")
    @PreAuth("hasAuthority('sys_permission_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.updateById(sysPermission);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限详情查询", notes = "根据id查询权限详细信息")
    @ResourcesMapping(element = "详情", code = "sys_permission_info")
    @PreAuth("hasAuthority('sys_permission_info')")
    @GetMapping("/{id}")
    public HttpResponse info(@PathVariable int id) {
        return HttpResponse.resultSuccess(sysPermissionService.selectById(id));
    }

    @ApiOperation(value = "权限是否已存在", notes = "根据SysPermission对象设定的字段值来查询判断")
    @ResourcesMapping(element = "修改", code = "sys_permission_exist")
    @PreAuth("hasAuthority('sys_permission_exist')")
    @GetMapping("/exist")
    public HttpResponse exist(SysPermission sysPermission) {
        if (sysPermission == null) {
            return HttpResponse.errorParams();
        }
        return HttpResponse.resultSuccess(sysPermissionService.selectOne(sysPermission) != null);
    }

    @ApiOperation(value = "权限关联菜单批量添加",  notes = "保存多个SysPermissionMenuPk对象")
    @ResourcesMapping(element = "添加", code = "sys_permission_menu_add")
    @PreAuth("hasAuthority('sys_permission_menu_add')")
    @PostMapping("/batchMenuAdd")
    public HttpResponse batchMenuAdd(@RequestBody List<SysPermissionMenuPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限关联菜单批量删除",  notes = "根据权限id和多个菜单id删除关联")
    @ResourcesMapping(element = "删除", code = "sys_permission_menu_delete")
    @PreAuth("hasAuthority('sys_permission_menu_delete')")
    @PostMapping("/batchMenuDelete")
    public HttpResponse batchMenuDelete(@Validated @RequestBody PermissionMenuDto dto) {
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

    @ApiOperation(value = "权限关联资源批量添加",  notes = "添加多个SysPermissionResourcePk对象")
    @ResourcesMapping(element = "添加", code = "sys_permission_resource_add")
    @PreAuth("hasAuthority('sys_permission_resource_add')")
    @PostMapping("/batchResourceAdd")
    public HttpResponse batchResourceAdd(@RequestBody List<SysPermissionResourcePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionResourceServicePk.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限关联资源批量删除",  notes = "根据权限id和多个资源id删除关联")
    @ResourcesMapping(element = "添加", code = "sys_permission_resource_delete")
    @PreAuth("hasAuthority('sys_permission_resource_delete')")
    @PostMapping("/batchResourceDelete")
    public HttpResponse batchResourceDelete(@Validated @RequestBody PermissionResourceDto dto) {
        if (dto.getResourceIds() == null || dto.getResourceIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionResourceServicePk.batchDelete(dto.getPermissionId(), dto.getResourceIds());
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限未关联资源查询", notes = "根据权限id查询该权限未关联的资源并返回资源列表")
    @ResourcesMapping(element = "查询", code = "sys_permission_no_resource_get")
    @PreAuth("hasAuthority('sys_permission_no_resource_get')")
    @GetMapping("/permissionNoRelationResourceList")
    public HttpResponse permissionNoRelationResourceList(PermissionResourceDto dto) {
        return new HttpResponse().success(sysPermissionResourceServicePk.loadPermissionNoRelationResources(dto));
    }

    @ApiOperation(value = "权限已关联资源查询",  notes = "根据权限id查询该权限已关联的资源并返回资源列表")
    @ResourcesMapping(element = "查询", code = "sys_permission_resource_get")
    @PreAuth("hasAuthority('sys_permission_resource_get')")
    @GetMapping("/permissionResourceList")
    public HttpResponse permissionResourceList(PermissionResourceDto dto) {
        return new HttpResponse().success(sysPermissionResourceServicePk.loadPermissionResources(dto));
    }

}
