/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nycloud-admin

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-10 20:13:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
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
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
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
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('frontend', '', 'frontend', 'all', 'password,refresh_token', null, null, '100000', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for oauth_client_token
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
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
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
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `path` varchar(50) DEFAULT NULL COMMENT '资源路径',
  `component` varchar(50) DEFAULT NULL COMMENT '前端组件',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `enable` int(1) DEFAULT '1' COMMENT '是否启用 1 为启用 0 为禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, null, '0', null, '1', 'systemAdmin', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '/sys/user', null, '1', '0', '2', 'systemAdminUser', '/user', '_import(\'admin/user/index\')', '用户管理菜单', '1');

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `code` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'SuperManager', '拥有系统所有权限');
INSERT INTO `sys_role` VALUES ('3', '普通管理员', 'OrdinaryManager', null);

-- ----------------------------
-- Table structure for sys_role_privilege_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege_pk`;
CREATE TABLE `sys_role_privilege_pk` (
  `role_id` bigint(20) DEFAULT NULL,
  `privilege_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_privilege_pk
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
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
  `nick_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'super', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('3', 'leson', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('4', 'student', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('5', 'baidu', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('6', 'tencent', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('7', 'alibaba', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('8', '360', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('9', 'jd', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('10', 'xiaomi', '123456', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('11', 'leshi', '123456', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_group
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
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', '管理员组', 'ManagerCode', null, null, '管理员用户组');

-- ----------------------------
-- Table structure for sys_user_group_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_pk`;
CREATE TABLE `sys_user_group_pk` (
  `user_group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group_pk
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_group_role_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_role_pk`;
CREATE TABLE `sys_user_group_role_pk` (
  `user_group_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group_role_pk
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_pk`;
CREATE TABLE `sys_user_role_pk` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role_pk
-- ----------------------------
