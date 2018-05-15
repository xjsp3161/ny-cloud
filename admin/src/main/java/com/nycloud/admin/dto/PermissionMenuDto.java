package com.nycloud.admin.dto;

import lombok.Data;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/15 0015
 * @modified By:
 * @version: 1.0
 **/
@Data
public class PermissionMenuDto {

    /**
     * 权限Id
     */
    private Integer permissionId;

    /**
     * 菜单名称 模糊检索
     */
    private String name;

}
