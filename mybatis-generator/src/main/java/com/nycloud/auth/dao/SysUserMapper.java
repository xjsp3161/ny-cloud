package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SysUserMapper {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sys_user (id, username, ",
        "password, create_time, ",
        "last_password_change, enable, ",
        "authorities, nick_name)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{createTime,jdbcType=BIGINT}, ",
        "#{lastPasswordChange,jdbcType=BIGINT}, #{enable,jdbcType=INTEGER}, ",
        "#{authorities,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR})"
    })
    int insert(SysUser record);

    @Select({
        "select",
        "id, username, password, create_time, last_password_change, enable, authorities, ",
        "nick_name",
        "from sys_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="last_password_change", property="lastPasswordChange", jdbcType=JdbcType.BIGINT),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER),
        @Result(column="authorities", property="authorities", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR)
    })
    SysUser selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, username, password, create_time, last_password_change, enable, authorities, ",
        "nick_name",
        "from sys_user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="last_password_change", property="lastPasswordChange", jdbcType=JdbcType.BIGINT),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER),
        @Result(column="authorities", property="authorities", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUser> selectAll();

    @Update({
        "update sys_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=BIGINT},",
          "last_password_change = #{lastPasswordChange,jdbcType=BIGINT},",
          "enable = #{enable,jdbcType=INTEGER},",
          "authorities = #{authorities,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUser record);
}