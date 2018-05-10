package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.service.SysRoleService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
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

}
