package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.model.SysUserGroup;
import com.nycloud.admin.service.SysRoleService;
import com.nycloud.admin.service.SysUserGroupService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
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
@RequestMapping(value = "api/sysUserGroup")
public class SysUserGroupController {

    @Autowired
    private SysUserGroupService sysUserGroupService;

    @GetMapping("")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysUserGroupService.findByPageList(requestDto));
    }

    @PostMapping
    public HttpResponse save(@RequestBody SysUserGroup sysUserGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysUserGroupService.insert(sysUserGroup);
        return new HttpResponse().success();
    }

}
