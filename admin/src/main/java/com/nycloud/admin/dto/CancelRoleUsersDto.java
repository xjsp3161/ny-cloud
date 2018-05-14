package com.nycloud.admin.dto;

import lombok.Data;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/14 0014
 * @modified By:
 * @version: 1.0
 **/
@Data
public class CancelRoleUsersDto {

    private Integer roleId;
    private Integer [] userIds;
}
