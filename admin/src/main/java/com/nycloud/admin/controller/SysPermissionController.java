package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.service.SysPermissionService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation(value = "权限添加", notes = "根据SysPermission对象创建权限")
    @PostMapping
    public HttpResponse add(@RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.insert(sysPermission);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限删除", notes = "根据权限id删除权限信息")
    @ResourcesMapping(elements = "删除", code = "sys_permission_delete")
    @PreAuth("hasAuthority('sys_permission_delete')")
    @DeleteMapping("/{id}")
    public HttpResponse delete(@PathVariable int id) {
        sysPermissionService.deleteById(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限查询", notes = "可分页并可根据权限名称模糊检索")
    @ResourcesMapping(elements = "查询", code = "sys_permission_query")
    @PreAuth("hasAuthority('sys_permission_query')")
    @GetMapping
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("name");
        return HttpResponse.resultSuccess(sysPermissionService.findByPageList(requestDto));
    }

    @ApiOperation(value = "权限修改", notes = "根据传递的SysPermission对象来更新, SysPermission对象必须包含id")
    @ResourcesMapping(elements = "修改", code = "sys_permission_update")
    @PreAuth("hasAuthority('sys_permission_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.updateById(sysPermission);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限是否已存在", notes = "根据SysPermission对象设定的字段值来查询判断")
    @ResourcesMapping(elements = "修改", code = "sys_permission_exist")
    @PreAuth("hasAuthority('sys_permission_exist')")
    @GetMapping("/exist")
    public HttpResponse exist(SysPermission sysPermission) {
        if (sysPermission == null) {
            return HttpResponse.errorParams();
        }
        return HttpResponse.resultSuccess(sysPermissionService.selectOne(sysPermission) != null);
    }

}
