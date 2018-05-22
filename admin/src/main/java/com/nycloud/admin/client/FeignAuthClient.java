package com.nycloud.admin.client;

import com.nycloud.admin.model.SysResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/22 0022
 * @modified By:
 * @version: 1.0
 **/
@FeignClient(value = "nycloud-auth-server")
public interface FeignAuthClient {

    /**
     * 远程获取用户资源
     * @param userId
     * @return
     */
    @GetMapping(value = "/userResources?userId={userId}")
    List<SysResource> getUserResources(@RequestParam(value = "userId") Integer userId);

}


