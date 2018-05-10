package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户列表分页")
    @GetMapping("")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("username");
        return new HttpResponse().success(sysUserService.findByPageList(requestDto));
    }

    @GetMapping("/{id}")
    public HttpResponse info(@PathVariable Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return new HttpResponse().success(sysUserService.selectOne(sysUser));
    }

}
