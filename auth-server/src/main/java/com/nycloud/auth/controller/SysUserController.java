package com.nycloud.auth.controller;


import com.nycloud.auth.dto.UserDto;
import com.nycloud.auth.model.SysUser;
import com.nycloud.auth.security.JwtUtil;
import com.nycloud.auth.service.SysUserService;
import com.nycloud.auth.util.Constants;
import com.nycloud.auth.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/26 0026
 * @modified By:
 * @version: 1.0
 **/
@RestController
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/public/login")
    public HttpResponse login(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        HttpResponse response = new HttpResponse();
        if (bindingResult.hasErrors()) {
            response.setCode(Constants.RESULT_PARAM_ERROR);
            response.setMsg("参数为空或参数错误");
        } else {
            SysUser sysUser = sysUserService.findByUserName(userDto.getUsername());
            if (sysUser != null) {
                if (!userDto.getPassword().equals(sysUser.getPassword())) {
                    response.setCode(Constants.RESULT_PASSWORD_ERROR);
                    response.setMsg("用户名或密码错误");
                } else {
                    Map<String, Object> userMaps = new HashMap<String, Object>(){
                        {
                            put("sub", sysUser.getUsername());
                            put("id", sysUser.getId());
                            put("nickName", sysUser.getNickName());
                            put("enable", sysUser.getEnable());
                        }
                    };
                    String token = this.jwtUtil.generateToken(userMaps);
                    response.setData(token);
                }
            } else {
                response.setCode(Constants.RESULT_NOT_USER);
                response.setMsg("用户不存在");
            }
        }
        return response;
    }

}

