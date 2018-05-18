package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.service.SysPermissionService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    @ApiOperation(value = "权限列表查询", notes = "可分页并可根据权限名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysPermissionService.findByPageList(requestDto));
    }

    @ApiOperation(value = "权限保存", notes = "根据SysPermission对象创建权限")
    @PostMapping
    public HttpResponse save(@RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.insert(sysPermission);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "权限是否已存在", notes = "根据SysPermission对象设定的字段值来查询判断")
    @GetMapping("/exist")
    public HttpResponse exist(SysPermission sysPermission) {
        if (sysPermission == null) {
            return HttpResponse.errorParams();
        }
        return HttpResponse.resultSuccess(sysPermissionService.selectOne(sysPermission) != null);
    }

}
