package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("")
    public List<SysUser> index() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setNickName("超级管理员");
        List list = new ArrayList(){{
            add(sysUser);
        }};
        return list;
    }

}
