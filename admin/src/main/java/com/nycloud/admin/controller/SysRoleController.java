package com.nycloud.admin.controller;

import com.nycloud.admin.dto.CancelRoleUsersDto;
import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.model.SysUserRolePk;
import com.nycloud.admin.service.SysRoleService;
import com.nycloud.admin.service.SysUserRolePkService;
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
 * @date: Created in 2018/5/10 0010
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRolePkService sysUserRolePkService;

    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysRoleService.findByPageList(requestDto));
    }

    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysRole role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysRoleService.insert(role);
        return new HttpResponse().success();
    }

    @PostMapping("/batchRoleUsers")
    public HttpResponse batchRoleUsers(@RequestBody List<SysUserRolePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchInsert(list);
        return new HttpResponse().success();
    }

    @PostMapping("/cancelRoleUsers")
    public HttpResponse cancelRoleUsers(@Validated @RequestBody CancelRoleUsersDto dto) {
        if (dto.getUserIds() == null || dto.getUserIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchDelete(dto.getRoleId(), dto.getUserIds());
        return new HttpResponse().success();
    }

}
