package com.nycloud.admin.controller;

import com.nycloud.admin.dto.PermissionMenuDto;
import com.nycloud.admin.model.SysPermissionMenuPk;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
import com.nycloud.common.vo.HttpResponse;
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
@RequestMapping(value = "api/sysPermissionMenu")
public class SysPermissionMenuController {

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @ApiOperation(value = "权限未关联菜单树查询", notes = "根据权限id查询该权限未关联的菜单并返回菜单树")
    @GetMapping("/permissionNoRelationMenuTree")
    public HttpResponse permissionNoRelationMenuTree(@RequestParam Integer permissionId) {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionNoMenuTree(permissionId));
    }

    @ApiOperation(value = "权限已关联菜单树查询",  notes = "根据权限id查询该权限已关联的菜单并返回菜单树")
    @GetMapping("/permissionMenuTree")
    public HttpResponse permissionMenuTree(@RequestParam Integer permissionId) {
        return new HttpResponse().success(sysPermissionMenuPkService.loadPermissionMenuTree(permissionId));
    }

    @ApiOperation(value = "权限关联菜单批量保存",  notes = "保存多个SysPermissionMenuPk对象")
    @PostMapping("/batchSave")
    public HttpResponse batchSave(@RequestBody List<SysPermissionMenuPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限关联菜单批量删除",  notes = "根据权限id和多个菜单id删除关联")
    @PostMapping("/batchDelete")
    public HttpResponse cancel(@Validated @RequestBody PermissionMenuDto dto) {
        if (dto.getMenuIds() == null || dto.getMenuIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchDelete(dto.getPermissionId(), dto.getMenuIds());
        return HttpResponse.resultSuccess();
    }

}
