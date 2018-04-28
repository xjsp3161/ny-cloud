package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysUserGroupRolePk;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SysUserGroupRolePkMapper {
    @Insert({
        "insert into sys_user_group_role_pk (user_group_id, role_id)",
        "values (#{userGroupId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})"
    })
    int insert(SysUserGroupRolePk record);

    @Select({
        "select",
        "user_group_id, role_id",
        "from sys_user_group_role_pk"
    })
    @Results({
        @Result(column="user_group_id", property="userGroupId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<SysUserGroupRolePk> selectAll();
}