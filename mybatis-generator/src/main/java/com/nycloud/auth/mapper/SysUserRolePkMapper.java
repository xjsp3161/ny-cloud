package com.nycloud.auth.mapper;

import com.nycloud.auth.model.SysUserRolePk;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SysUserRolePkMapper {
    @Insert({
        "insert into sys_user_role_pk (user_id, role_id)",
        "values (#{userId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})"
    })
    int insert(SysUserRolePk record);

    @Select({
        "select",
        "user_id, role_id",
        "from sys_user_role_pk"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<SysUserRolePk> selectAll();
}