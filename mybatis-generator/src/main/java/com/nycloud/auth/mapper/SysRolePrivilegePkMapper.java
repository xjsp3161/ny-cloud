package com.nycloud.auth.mapper;

import com.nycloud.auth.model.SysRolePrivilegePk;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SysRolePrivilegePkMapper {
    @Insert({
        "insert into sys_role_privilege_pk (role_id, privilege_id)",
        "values (#{roleId,jdbcType=BIGINT}, #{privilegeId,jdbcType=BIGINT})"
    })
    int insert(SysRolePrivilegePk record);

    @Select({
        "select",
        "role_id, privilege_id",
        "from sys_role_privilege_pk"
    })
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="privilege_id", property="privilegeId", jdbcType=JdbcType.BIGINT)
    })
    List<SysRolePrivilegePk> selectAll();
}