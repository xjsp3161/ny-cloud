package com.nycloud.admin.controller;

import com.nycloud.admin.dto.PermissionResourceDto;
import com.nycloud.admin.model.SysPermissionResourcePk;
import com.nycloud.admin.service.SysPermissionResourceServicePk;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
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
@RestController
@RequestMapping(value = "api/sysPermissionResource")
public class SysPermissionResourceController {

    @Autowired
    private SysPermissionResourceServicePk sysPermissionResourceServicePk;

    @ApiOperation(value = "权限关联资源批量添加",  notes = "添加多个SysPermissionResourcePk对象")
    @ResourcesMapping(elements = "添加", code = "sys_permission_resource_add")
    @PreAuth("hasAuthority('sys_permission_resource_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysPermissionResourcePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionResourceServicePk.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限关联资源批量删除",  notes = "根据权限id和多个资源id删除关联")
    @ResourcesMapping(elements = "添加", code = "sys_permission_resource_delete")
    @PreAuth("hasAuthority('sys_permission_resource_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody PermissionResourceDto dto) {
        if (dto.getResourceIds() == null || dto.getResourceIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionResourceServicePk.batchDelete(dto.getPermissionId(), dto.getResourceIds());
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限未关联资源查询", notes = "根据权限id查询该权限未关联的资源并返回资源列表")
    @ResourcesMapping(elements = "查询", code = "sys_permission_no_resource_get")
    @PreAuth("hasAuthority('sys_permission_no_resource_get')")
    @GetMapping("/permissionNoRelationResourceList")
    public HttpResponse permissionNoRelationResourceList(PermissionResourceDto dto) {
        return new HttpResponse().success(sysPermissionResourceServicePk.loadPermissionNoRelationResources(dto));
    }

    @ApiOperation(value = "权限已关联资源查询",  notes = "根据权限id查询该权限已关联的资源并返回资源列表")
    @ResourcesMapping(elements = "查询", code = "sys_permission_resource_get")
    @PreAuth("hasAuthority('sys_permission_resource_get')")
    @GetMapping("/permissionResourceList")
    public HttpResponse permissionResourceList(PermissionResourceDto dto) {
        return new HttpResponse().success(sysPermissionResourceServicePk.loadPermissionResources(dto));
    }


}
