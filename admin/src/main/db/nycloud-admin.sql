/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : nycloud-admin

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 05/18/2018 22:59:48 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(36) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(36) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `oauth_client_details`
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('frontend', '', 'frontend', 'all', 'password,refresh_token', null, null, '100000', null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级Id',
  `sort` int(2) DEFAULT NULL COMMENT '排序',
  `level` int(2) NOT NULL COMMENT '菜单等级',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `path` varchar(50) DEFAULT NULL COMMENT '资源路径',
  `component` varchar(50) DEFAULT NULL COMMENT '前端组件',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `enable` int(1) DEFAULT '1' COMMENT '是否启用 1 为启用 0 为禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, 'example', '0', null, '1', 'systemAdmin', '/sys/admin', null, null, '1'), ('2', '用户管理', '/admin/sys/sysUser', '', '1', '1', '2', 'systemAdminUser', 'user', 'admin/user/index', '用户管理菜单', '1'), ('3', '菜单管理', '/admin/sys/sysMenu', null, '1', '2', '2', 'systemAdminMenu', 'menu', 'admin/menu/index', null, '1'), ('4', '角色管理', '/admin/api/sysRole', null, '1', '3', '2', 'systemAdminRole', 'role', 'admin/role/index', null, '1'), ('5', '用户组管理', '/admin/api/sysUserGroup', null, '1', '4', '2', 'systemAdminUserGroup', 'group', 'admin/userGroup/index', null, '1'), ('6', '资源管理', '/admin/api/sysResource', null, '1', '5', '2', 'systemAdminResource', 'resource', 'admin/resource/index', null, '1'), ('7', '权限管理', '/admin/api/sysPermission', null, '1', '6', '2', 'systemAdminPermission', 'permission', 'admin/permission/index', null, '1'), ('8', '测试', null, null, '1', '0', '2', 'test', '/sys/admin/test', 'admin/atest/index', null, '1'), ('9', '测试1', 'sys/admin/test/test1', null, '8', '1', '3', 'test1', 'test1', 'admin/atest/test1/index', null, '1'), ('10', '测试2', null, null, '8', '2', '3', 'test2', 'test2', 'admin/atest/test2/index', null, '1'), ('11', '测试3', null, null, '8', '3', '3', 'test3', '/sys/admin/test3', 'admin/atest/test3/index', null, '1'), ('12', '测试3-1', null, null, '11', '1', '4', 'test3-1', 'test3-1', 'admin/atest/test3/test3-1/index', null, '1'), ('13', '测试3-2', null, null, '11', '2', '4', 'test3-2', 'test3-2', 'admin/atest/test3/test3-2/index', null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(30) NOT NULL COMMENT '权限名称',
  `description` varchar(30) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '超级管理员权限', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission_menu_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_menu_pk`;
CREATE TABLE `sys_permission_menu_pk` (
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_permission_menu_pk`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '1'), ('1', '8'), ('1', '9'), ('1', '10'), ('1', '11'), ('1', '12'), ('1', '13'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('1', '6'), ('1', '7');
COMMIT;

-- ----------------------------
--  Table structure for `sys_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) DEFAULT NULL COMMENT '资源接口URL',
  `url_request_type` varchar(255) DEFAULT NULL COMMENT '资源接口URL请求类型',
  `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_resource`
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES ('29', '菜单列表查询', 'api/sysMenu', 'GET', '可分页并可根据菜单名称模糊检索'), ('30', '菜单更新', 'api/sysMenu', 'PUT', '根据传递的SysMenu对象来更新, SysMenu对象必须包含id'), ('31', '菜单详情查询', 'api/sysMenu/info', 'DELETE', '根据id查询菜单详细信息'), ('32', '菜单保存', 'api/sysMenu', 'POST', '根据SysMenu对象创建用户'), ('33', '菜单树查询', 'api/sysMenu/tree', 'GET', '查询所有可用菜单并返回树状结构'), ('34', '权限列表查询', 'api/sysPermission', 'GET', '可分页并可根据权限名称模糊检索'), ('35', '权限保存', 'api/sysPermission', 'POST', '根据SysPermission对象创建权限'), ('36', '权限是否已存在', 'api/sysPermission/exist', 'GET', '根据SysPermission对象设定的字段值来查询判断'), ('37', '权限关联菜单批量删除', 'api/sysPermissionMenu/batchDelete', 'POST', '根据权限id和多个菜单id删除关联'), ('38', '权限关联菜单批量保存', 'api/sysPermissionMenu/batchSave', 'POST', '保存多个SysPermissionMenuPk对象'), ('39', '权限已关联菜单树查询', 'api/sysPermissionMenu/permissionMenuTree', 'GET', '根据权限id查询该权限已关联的菜单并返回菜单树'), ('40', '权限未关联菜单树查询', 'api/sysPermissionMenu/permissionNoRelationMenuTree', 'GET', '根据权限id查询该权限未关联的菜单并返回菜单树'), ('41', '资源列表查询', 'api/sysResource', 'GET', '可分页并可根据权限名称模糊检索'), ('42', '资源保存', 'api/sysResource', 'POST', '根据SysResource对象创建资源'), ('43', '资源详情查询', 'api/sysResource/info', 'GET', '根据id查询资源详细信息'), ('44', '资源是否已存在', 'api/sysResource/exist', 'GET', '根据SysResource对象设定的字段值来查询判断'), ('45', '角色列表查询', 'api/sysRole', 'GET', '可分页并可根据角色名称模糊检索'), ('46', '角色保存', 'api/sysRole', 'POST', '根据SysRole对象创建角色'), ('47', '用户列表查询', 'api/sysUser', 'GET', '可分页并可根据用户名称模糊检索'), ('48', '用户详情查询', 'api/sysUser/info', 'GET', '根据id查询用户详细信息'), ('49', '用户可用菜单树查询', 'api/sysUser/userMenuTree', 'GET', '根据用户权限查询已分配好的菜单'), ('50', '用户组列表查询', 'api/sysUserGroup', 'GET', '可分页并可根据用户组名称模糊检索'), ('51', '用户组保存', 'api/sysUserGroup', 'POST', '根据SysUserGroup对象创建用户组'), ('52', '用户关联角色批量删除', 'api/sysUserRole/batchDelete', 'POST', '根据用户id和多个角色id删除关联'), ('53', '用户关联角色批量保存', 'api/sysUserRole/batchSave', 'POST', '保存多个SysUserRolePk对象'), ('54', '用户已关联角色查询', 'api/sysUserRole/userRoleList', 'GET', '根据用户id查询该用户已关联的角色并返回角色列表'), ('55', '用户未关联角色查询', 'api/sysUserRole/userNoRelationRoleList', 'GET', '根据用户id查询该用户未关联的角色并返回角色列表');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `code` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'SuperManager', '拥有系统所有权限'), ('2', '普通管理员', 'OrdinaryManager', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_permission_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_pk`;
CREATE TABLE `sys_role_permission_pk` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_privilege_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege_pk`;
CREATE TABLE `sys_role_privilege_pk` (
  `role_id` bigint(20) DEFAULT NULL,
  `privilege_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_password_change` bigint(20) DEFAULT NULL,
  `enable` int(1) DEFAULT NULL,
  `authorities` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', null, null, null, null, '管理员'), ('2', 'super', '123456', null, null, null, null, '超人'), ('3', 'leson', '123456', null, null, null, null, '王大锤'), ('4', 'student', '123456', null, null, null, null, '烟花易冷'), ('5', 'baidu', '123456', null, null, null, null, '百度科技'), ('6', 'tencent', '123456', null, null, null, null, '腾讯控股'), ('7', 'alibaba', '123456', null, null, null, null, '阿里巴巴'), ('8', '360', '123456', null, null, null, null, '360安全卫士'), ('9', 'jd', '123456', null, null, null, null, '京东商城'), ('10', 'xiaomi', '123456', null, null, null, null, '小米科技'), ('11', 'leshi', '123456', null, null, null, null, '乐视网');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_name` varchar(30) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_group`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_group` VALUES ('1', '管理员组', 'ManagerCode', null, null, '管理员用户组');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_group_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_pk`;
CREATE TABLE `sys_user_group_pk` (
  `user_group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_group_role_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_role_pk`;
CREATE TABLE `sys_user_group_role_pk` (
  `user_group_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role_pk`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_pk`;
CREATE TABLE `sys_user_role_pk` (
  `user_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_role_pk`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role_pk` VALUES ('3', '1'), ('4', '2'), ('4', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
