package com.nycloud.admin.vo;

import com.nycloud.admin.model.SysUser;
import lombok.Data;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/6/12 0012
 * @modified By:
 * @version: 1.0
 **/
@Data
public class SysUserInfo extends SysUser{

    /**
     * 用户组Id
     */
    private Integer groupId;

    /**
     * 用户组名称
     */
    private String groupName;

}
