package com.nycloud.admin.controller;

import com.nycloud.admin.dto.CancelPermissionDto;
import com.nycloud.admin.dto.CancelRoleUsersDto;
import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.model.SysPermissionMenuPk;
import com.nycloud.admin.service.SysPermissionMenuPkService;
import com.nycloud.admin.service.SysPermissionService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
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
@RestController
@RequestMapping(value = "api/sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysPermissionMenuPkService sysPermissionMenuPkService;

    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysPermissionService.findByPageList(requestDto));
    }

    @PostMapping
    public HttpResponse save(@RequestBody SysPermission sysPermission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysPermissionService.insert(sysPermission);
        return new HttpResponse().success();
    }

    @GetMapping("/exist")
    public HttpResponse exist(SysPermission sysPermission) {
        if (sysPermission == null) {
            return HttpResponse.errorParams();
        }
        return new HttpResponse().success(sysPermissionService.selectOne(sysPermission) != null);
    }

    @PostMapping("/batchPermissionMenus")
    public HttpResponse batchPermissionMenus(@RequestBody List<SysPermissionMenuPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchInsert(list);
        return new HttpResponse().success();
    }

    @PostMapping("/cancelPermissionMenus")
    public HttpResponse cancelPermissionMenus(@Validated @RequestBody CancelPermissionDto dto) {
        if (dto.getMenuIds() == null || dto.getMenuIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysPermissionMenuPkService.batchDelete(dto.getPermissionId(), dto.getMenuIds());
        return new HttpResponse().success();
    }

}
