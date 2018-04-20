package com.nycloud.admin.api;

import com.nycloud.common.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/20 0020
 * @modified By:
 * @version: 1.0
 **/
@RestController
public class SysUserController {

    private static final String URL = "api/sys/user";

    @RequestMapping(value = URL + "", method = RequestMethod.GET)
    public HttpResponse list() {
        return new HttpResponse().success();
    }

}
