package com.nycloud.auth.controller;

import com.nycloud.auth.model.SysResource;
import com.nycloud.auth.model.SysUser;
import com.nycloud.auth.service.SysUserService;
import com.nycloud.common.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by wangyunfei on 2017/6/12.
 */
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @GetMapping("/userResources")
    public HttpResponse<SysUser> userResources(@RequestParam Long userId) {
        return HttpResponse.resultSuccess(sysUserService.selectUserResources(userId));
    }

}
