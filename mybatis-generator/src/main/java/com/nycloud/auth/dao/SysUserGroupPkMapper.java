package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysUserGroupPk;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SysUserGroupPkMapper {
    @Insert({
        "insert into sys_user_group_pk (user_group_id, user_id)",
        "values (#{userGroupId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT})"
    })
    int insert(SysUserGroupPk record);

    @Select({
        "select",
        "user_group_id, user_id",
        "from sys_user_group_pk"
    })
    @Results({
        @Result(column="user_group_id", property="userGroupId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT)
    })
    List<SysUserGroupPk> selectAll();
}