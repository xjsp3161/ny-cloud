package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

/**
 * @author spuer.wu
 */
@Mapper
public interface SysUserMapper {

    @Select({
        "select",
        "id, username, password",
        "from sys_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    SysUser selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, username, password",
            "from sys_user where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    SysUser findSysUserByUserName(@Param("username") String username);


}