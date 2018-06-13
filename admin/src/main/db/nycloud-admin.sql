/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nycloud-admin

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-13 17:47:11
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
INSERT INTO `oauth_access_token` VALUES ('e30858207ac38ca750bb0649a75623e4', 0xACED0005737200436F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F4175746832416363657373546F6B656E0CB29E361B24FACE0200064C00156164646974696F6E616C496E666F726D6174696F6E74000F4C6A6176612F7574696C2F4D61703B4C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B4C000C72656672657368546F6B656E74003F4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F636F6D6D6F6E2F4F417574683252656672657368546F6B656E3B4C000573636F706574000F4C6A6176612F7574696C2F5365743B4C0009746F6B656E547970657400124C6A6176612F6C616E672F537472696E673B4C000576616C756571007E000578707372001E6A6176612E7574696C2E436F6C6C656374696F6E7324456D7074794D6170593614855ADCE7D002000078707372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001639C8C1A78787372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FDF47639DD0C9B70200014C000A65787069726174696F6E71007E0002787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73E10E0A6354D45E0200014C000576616C756571007E0005787074002431396164613665392D316562382D343331652D393035372D3061663865636433656232637371007E00097708000001643115017678737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C0001637400164C6A6176612F7574696C2F436F6C6C656374696F6E3B7870737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000023F40000000000001740003616C6C7874000662656172657274002434316239333636382D343531362D346162302D383737622D383063386335646662643233, '1b386ea344a7a88a508ea8d5e3d50439', 'admin', 'frontend', 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A657870000000007704000000007871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B72656469726563745572697400124C6A6176612F6C616E672F537472696E673B4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0011787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000F4C001172657175657374506172616D657465727371007E000E4C000573636F706571007E0011787074000866726F6E74656E64737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E000E7870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000677080000000800000003740006636C69656E7474000866726F6E74656E6474000A6772616E745F7479706574000870617373776F7264740008757365726E616D6574000561646D696E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F40000000000001740003616C6C78017371007E0022770C000000103F40000000000000787371007E00173F40000000000000770800000010000000007870707371007E0022770C000000103F40000000000000787371007E0022770C000000103F40000000000000787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001F40200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B000000007704000000007871007E002C737200176A6176612E7574696C2E4C696E6B6564486173684D617034C04E5C106CC0FB0200015A000B6163636573734F726465727871007E00173F400000000000067708000000080000000371007E001971007E001A71007E001B71007E001C71007E001D71007E001E780070737200326F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657200000000000001F40200075A00116163636F756E744E6F6E457870697265645A00106163636F756E744E6F6E4C6F636B65645A001563726564656E7469616C734E6F6E457870697265645A0007656E61626C65644C000B617574686F72697469657371007E00114C000870617373776F726471007E000F4C0008757365726E616D6571007E000F7870010101017371007E001F737200116A6176612E7574696C2E54726565536574DD98509395ED875B0300007870737200466F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657224417574686F72697479436F6D70617261746F7200000000000001F40200007870770400000000787074000561646D696E, '3196d476493b80423bd163beed633d5c');

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
INSERT INTO `oauth_refresh_token` VALUES ('6134dd94d0782b4fd9716c67ba5c649c', 0xACED00057372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FDF47639DD0C9B70200014C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73E10E0A6354D45E0200014C000576616C75657400124C6A6176612F6C616E672F537472696E673B787074016165794A68624763694F694A49557A49314E694973496E523563434936496B705856434A392E65794A316332567958323568625755694F694A685A47317062694973496E4E6A6233426C496A7062496D467362434A644C434A6864476B694F69497A4E7A51325A546C6D4D5330334F5445334C545133596A5174595749774D6930355954526C4F444E684D6D45304D4759694C434A6C654841694F6A45314D6A6B334E444D794E444D73496C6774515539495479315663325679535751694F6949784F5459324D5467324F4459784D7A41314E6A55784D6A41694C434A7164476B694F6949334E4445354E54517A4D5330355A6A55344C5451354E6A41744F5451784D5330784E7A67344D474A6A4F4449794D6A6B694C434A6A62476C6C626E5266615751694F694A6D636D3975644756755A434A392E46746C3333626431537438515758616174363357646141383764486E5A5A7548744A74504465397377476F7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001642BCD77F378, 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A657870000000007704000000007871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B72656469726563745572697400124C6A6176612F6C616E672F537472696E673B4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0011787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000F4C001172657175657374506172616D657465727371007E000E4C000573636F706571007E0011787074000866726F6E74656E64737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E000E7870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000677080000000800000003740006636C69656E7474000866726F6E74656E6474000A6772616E745F7479706574000870617373776F7264740008757365726E616D6574000561646D696E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F40000000000001740003616C6C78017371007E0022770C000000103F40000000000000787371007E00173F40000000000000770800000010000000007870707371007E0022770C000000103F40000000000000787371007E0022770C000000103F400000000000007873720038636F6D2E6E79636C6F75642E617574682E636F6E6669672E637573746F6D2E437573746F6D41757468656E7469636174696F6E546F6B656E9F36D58D3B5B5BB20200014C000B7573657244657461696C737400324C636F6D2F6E79636C6F75642F617574682F636F6E6669672F637573746F6D2F437573746F6D5573657244657461696C733B7871007E0003017372001F6A6176612E7574696C2E436F6C6C656374696F6E7324456D7074794C6973747AB817B43CA79EDE0200007870737200176A6176612E7574696C2E4C696E6B6564486173684D617034C04E5C106CC0FB0200015A000B6163636573734F726465727871007E00173F400000000000067708000000080000000371007E001971007E001A71007E001B71007E001C71007E001D71007E001E780073720030636F6D2E6E79636C6F75642E617574682E636F6E6669672E637573746F6D2E437573746F6D5573657244657461696C7396AE867D7444C0210200065A0007656E61626C65644C000B617574686F72697469657371007E00044C0008636C69656E74496471007E000F4C000870617373776F726471007E000F4C000675736572496471007E000F4C0008757365726E616D6571007E000F7870017071007E001A74000631323334353674001231393636313836383631333035363531323071007E001E);
INSERT INTO `oauth_refresh_token` VALUES ('3196d476493b80423bd163beed633d5c', 0xACED00057372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FDF47639DD0C9B70200014C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73E10E0A6354D45E0200014C000576616C75657400124C6A6176612F6C616E672F537472696E673B787074002431396164613665392D316562382D343331652D393035372D3061663865636433656232637372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001643115017678, 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A657870000000007704000000007871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B72656469726563745572697400124C6A6176612F6C616E672F537472696E673B4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0011787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000F4C001172657175657374506172616D657465727371007E000E4C000573636F706571007E0011787074000866726F6E74656E64737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E000E7870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000677080000000800000003740006636C69656E7474000866726F6E74656E6474000A6772616E745F7479706574000870617373776F7264740008757365726E616D6574000561646D696E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F40000000000001740003616C6C78017371007E0022770C000000103F40000000000000787371007E00173F40000000000000770800000010000000007870707371007E0022770C000000103F40000000000000787371007E0022770C000000103F40000000000000787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001F40200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B000000007704000000007871007E002C737200176A6176612E7574696C2E4C696E6B6564486173684D617034C04E5C106CC0FB0200015A000B6163636573734F726465727871007E00173F400000000000067708000000080000000371007E001971007E001A71007E001B71007E001C71007E001D71007E001E780070737200326F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657200000000000001F40200075A00116163636F756E744E6F6E457870697265645A00106163636F756E744E6F6E4C6F636B65645A001563726564656E7469616C734E6F6E457870697265645A0007656E61626C65644C000B617574686F72697469657371007E00114C000870617373776F726471007E000F4C0008757365726E616D6571007E000F7870010101017371007E001F737200116A6176612E7574696C2E54726565536574DD98509395ED875B0300007870737200466F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657224417574686F72697479436F6D70617261746F7200000000000001F40200007870770400000000787074000561646D696E);

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
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `path` varchar(50) DEFAULT NULL COMMENT '资源路径',
  `component` varchar(50) DEFAULT NULL COMMENT '前端组件',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `enable` int(1) DEFAULT '1' COMMENT '是否启用 1 为启用 0 为禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, 'example', '0', null, '1', 'systemAdmin', '/sys/admin', null, null, '1');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '/admin/sys/sysUser', '', '1', '1', '2', 'systemAdminUser', 'user', 'admin/user/index', '用户管理菜单', '1');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', '/admin/sys/sysMenu', null, '1', '2', '2', 'systemAdminMenu', 'menu', 'admin/menu/index', null, '1');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', '/admin/api/sysRole', null, '1', '3', '2', 'systemAdminRole', 'role', 'admin/role/index', null, '1');
INSERT INTO `sys_menu` VALUES ('5', '用户组管理', '/admin/api/sysUserGroup', null, '1', '4', '2', 'systemAdminUserGroup', 'group', 'admin/userGroup/index', null, '1');
INSERT INTO `sys_menu` VALUES ('6', '资源管理', '/admin/api/sysResource', null, '1', '5', '2', 'systemAdminResource', 'resource', 'admin/resource/index', null, '1');
INSERT INTO `sys_menu` VALUES ('7', '权限管理', '/admin/api/sysPermission', null, '1', '6', '2', 'systemAdminPermission', 'permission', 'admin/permission/index', null, '1');
INSERT INTO `sys_menu` VALUES ('8', '测试', null, null, '1', '0', '2', 'test', '/sys/admin/test', 'admin/atest/index', null, '1');
INSERT INTO `sys_menu` VALUES ('9', '测试1', 'sys/admin/test/test1', null, '8', '1', '3', 'test1', 'test1', 'admin/atest/test1/index', null, '1');
INSERT INTO `sys_menu` VALUES ('10', '测试2', null, null, '8', '2', '3', 'test2', 'test2', 'admin/atest/test2/index', null, '1');
INSERT INTO `sys_menu` VALUES ('11', '测试3', null, null, '8', '3', '3', 'test3', '/sys/admin/test3', 'admin/atest/test3/index', null, '1');
INSERT INTO `sys_menu` VALUES ('12', '测试3-1', null, null, '11', '1', '4', 'test3-1', 'test3-1', 'admin/atest/test3/test3-1/index', null, '1');
INSERT INTO `sys_menu` VALUES ('13', '测试3-2', null, null, '11', '2', '4', 'test3-2', 'test3-2', 'admin/atest/test3/test3-2/index', null, '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(30) NOT NULL COMMENT '权限名称',
  `description` varchar(30) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '超级管理员权限', null);

-- ----------------------------
-- Table structure for sys_permission_menu_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_menu_pk`;
CREATE TABLE `sys_permission_menu_pk` (
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_menu_pk
-- ----------------------------
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '1');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '8');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '9');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '10');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '11');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '12');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '13');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '2');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '3');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '4');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '5');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '6');
INSERT INTO `sys_permission_menu_pk` VALUES ('1', '7');

-- ----------------------------
-- Table structure for sys_permission_resource_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource_pk`;
CREATE TABLE `sys_permission_resource_pk` (
  `permission_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_resource_pk
-- ----------------------------
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '405');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '406');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '407');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '408');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '409');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '410');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '411');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '412');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '413');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '414');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '415');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '416');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '417');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '418');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '419');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '420');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '421');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '422');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '423');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '424');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '425');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '426');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '427');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '428');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '429');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '430');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '432');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '433');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '434');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '435');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '436');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '437');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '438');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '439');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '440');
INSERT INTO `sys_permission_resource_pk` VALUES ('1', '431');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `code` varchar(100) DEFAULT NULL COMMENT '资源编码',
  `page_elements` varchar(100) DEFAULT NULL COMMENT '页面元素',
  `url` varchar(100) DEFAULT NULL COMMENT '资源接口URL',
  `url_request_type` varchar(100) DEFAULT NULL COMMENT '资源接口URL请求类型',
  `description` varchar(100) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('405', '菜单列表查询', '', '', 'api/sysMenu', 'GET', '可分页并可根据菜单名称模糊检索');
INSERT INTO `sys_resource` VALUES ('406', '菜单更新', '', '', 'api/sysMenu', 'PUT', '根据传递的SysMenu对象来更新, SysMenu对象必须包含id');
INSERT INTO `sys_resource` VALUES ('407', '菜单详情查询', '', '', 'api/sysMenu/info', 'DELETE', '根据id查询菜单详细信息');
INSERT INTO `sys_resource` VALUES ('408', '菜单保存', '', '', 'api/sysMenu', 'POST', '根据SysMenu对象创建用户');
INSERT INTO `sys_resource` VALUES ('409', '菜单树查询', '', '', 'api/sysMenu/tree', 'GET', '查询所有可用菜单并返回树状结构');
INSERT INTO `sys_resource` VALUES ('410', '权限列表查询', '', '', 'api/sysPermission', 'GET', '可分页并可根据权限名称模糊检索');
INSERT INTO `sys_resource` VALUES ('411', '权限保存', '', '', 'api/sysPermission', 'POST', '根据SysPermission对象创建权限');
INSERT INTO `sys_resource` VALUES ('412', '权限是否已存在', '', '', 'api/sysPermission/exist', 'GET', '根据SysPermission对象设定的字段值来查询判断');
INSERT INTO `sys_resource` VALUES ('413', '权限关联菜单批量删除', '', '', 'api/sysPermissionMenu/batchDelete', 'POST', '根据权限id和多个菜单id删除关联');
INSERT INTO `sys_resource` VALUES ('414', '权限关联菜单批量保存', '', '', 'api/sysPermissionMenu/batchSave', 'POST', '保存多个SysPermissionMenuPk对象');
INSERT INTO `sys_resource` VALUES ('415', '权限未关联菜单树查询', '', '', 'api/sysPermissionMenu/permissionNoRelationMenuTree', 'GET', '根据权限id查询该权限未关联的菜单并返回菜单树');
INSERT INTO `sys_resource` VALUES ('416', '权限已关联菜单树查询', '', '', 'api/sysPermissionMenu/permissionMenuTree', 'GET', '根据权限id查询该权限已关联的菜单并返回菜单树');
INSERT INTO `sys_resource` VALUES ('417', '权限关联资源批量删除', '', '', 'api/sysPermissionResource/batchDelete', 'POST', '根据权限id和多个资源id删除关联');
INSERT INTO `sys_resource` VALUES ('418', '权限关联资源批量保存', '', '', 'api/sysPermissionResource/batchSave', 'POST', '保存多个SysPermissionResourcePk对象');
INSERT INTO `sys_resource` VALUES ('419', '权限未关联资源查询', '', '', 'api/sysPermissionResource/permissionNoRelationResourceList', 'GET', '根据权限id查询该权限未关联的资源并返回资源列表');
INSERT INTO `sys_resource` VALUES ('420', '权限已关联资源查询', '', '', 'api/sysPermissionResource/permissionResourceList', 'GET', '根据权限id查询该权限已关联的资源并返回资源列表');
INSERT INTO `sys_resource` VALUES ('421', '资源列表查询', '', '', 'api/sysResource', 'GET', '可分页并可根据权限名称模糊检索');
INSERT INTO `sys_resource` VALUES ('422', '资源保存', '', '', 'api/sysResource', 'POST', '根据SysResource对象创建资源');
INSERT INTO `sys_resource` VALUES ('423', '资源详情查询', '', '', 'api/sysResource/info', 'GET', '根据id查询资源详细信息');
INSERT INTO `sys_resource` VALUES ('424', '资源是否已存在', '', '', 'api/sysResource/exist', 'GET', '根据SysResource对象设定的字段值来查询判断');
INSERT INTO `sys_resource` VALUES ('425', '角色列表查询', '', '', 'api/sysRole', 'GET', '可分页并可根据角色名称模糊检索');
INSERT INTO `sys_resource` VALUES ('426', '角色保存', '', '', 'api/sysRole', 'POST', '根据SysRole对象创建角色');
INSERT INTO `sys_resource` VALUES ('427', '角色关联权限批量删除', '', '', 'api/sysRolePermission/batchDelete', 'POST', '根据角色id和多个权限id删除关联');
INSERT INTO `sys_resource` VALUES ('428', '角色关联权限批量保存', '', '', 'api/sysRolePermission/batchSave', 'POST', '保存多个SysRolePermissionPk对象');
INSERT INTO `sys_resource` VALUES ('429', '角色已关联权限查询', '', '', 'api/sysRolePermission/rolePermissionList', 'GET', '根据角色id查询该角色已关联的权限并返回权限列表');
INSERT INTO `sys_resource` VALUES ('430', '角色未关联权限查询', '', '', 'api/sysRolePermission/roleNoRelationPermissionList', 'GET', '根据角色id查询该角色未关联的权限并返回权限列表');
INSERT INTO `sys_resource` VALUES ('431', '用户列表查询', 'sys_user_get', '查询', 'api/sysUser', 'GET', '可分页并可根据用户名称模糊检索');
INSERT INTO `sys_resource` VALUES ('432', '用户详情查询', '', '', 'api/sysUser/info', 'GET', '根据id查询用户详细信息');
INSERT INTO `sys_resource` VALUES ('433', '用户所有可用资源查询', '', '', 'public/api/sysUser/userResources', 'GET', '根据用户Id查询分配的角色权限下面的资源列表');
INSERT INTO `sys_resource` VALUES ('434', '用户可用菜单树查询', '', '', 'api/sysUser/userMenuTree', 'GET', '根据用户权限查询已分配好的菜单');
INSERT INTO `sys_resource` VALUES ('435', '用户组列表查询', '', '', 'api/sysUserGroup', 'GET', '可分页并可根据用户组名称模糊检索');
INSERT INTO `sys_resource` VALUES ('436', '用户组保存', '', '', 'api/sysUserGroup', 'POST', '根据SysUserGroup对象创建用户组');
INSERT INTO `sys_resource` VALUES ('437', '用户关联角色批量删除', '', '', 'api/sysUserRole/batchDelete', 'POST', '根据用户id和多个角色id删除关联');
INSERT INTO `sys_resource` VALUES ('438', '用户关联角色批量保存', '', '', 'api/sysUserRole/batchSave', 'POST', '保存多个SysUserRolePk对象');
INSERT INTO `sys_resource` VALUES ('439', '用户已关联角色查询', '', '', 'api/sysUserRole/userRoleList', 'GET', '根据用户id查询该用户已关联的角色并返回角色列表');
INSERT INTO `sys_resource` VALUES ('440', '用户未关联角色查询', '', '', 'api/sysUserRole/userNoRelationRoleList', 'GET', '根据用户id查询该用户未关联的角色并返回角色列表');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `code` varchar(30) NOT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`) USING HASH,
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'SUPER_ADMIN', '拥有系统所有权限');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', 'OrdinaryManager', null);

-- ----------------------------
-- Table structure for sys_role_permission_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_pk`;
CREATE TABLE `sys_role_permission_pk` (
  `role_id` int(20) DEFAULT NULL,
  `permission_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission_pk
-- ----------------------------
INSERT INTO `sys_role_permission_pk` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_password_change` bigint(20) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `authorities` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `face_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('196618686130565120', 'admin', '123456', '1528790890539', '1528790890539', '1', null, '管理员', null);
INSERT INTO `sys_user` VALUES ('196618686130565121', 'super', '123456', '1528790890539', '1528790890539', '1', null, '超人', null);
INSERT INTO `sys_user` VALUES ('196618686130565122', 'leson', '123456', '1528790890539', '1528790890539', '1', null, '王大锤', null);
INSERT INTO `sys_user` VALUES ('196618686130565123', 'student', '123456', '1528790890539', '1528790890539', '1', null, '烟花易冷', null);
INSERT INTO `sys_user` VALUES ('196618686130565124', 'baidu', '123456', '1528790890539', '1528790890539', '1', null, '百度科技', null);
INSERT INTO `sys_user` VALUES ('196618686130565125', 'tencent', '123456', '1528790890539', '1528790890539', '1', null, '腾讯控股', null);
INSERT INTO `sys_user` VALUES ('196618686130565126', 'alibaba', '123456', '1528790890539', '1528790890539', '1', null, '阿里巴巴', null);
INSERT INTO `sys_user` VALUES ('196618686130565127', '360', '123456', '1528790890539', '1528790890539', '1', null, '360安全卫士', null);
INSERT INTO `sys_user` VALUES ('196618686130565128', 'jd', '123456', '1528790890539', '1528790890539', '1', null, '京东商城', null);
INSERT INTO `sys_user` VALUES ('196618686130565129', 'xiaomi', '123456', '1528790890539', '1528790890539', '1', null, '小米科技', null);
INSERT INTO `sys_user` VALUES ('196618686130565130', 'leshi', '123456', '1528790890539', '1528790890539', '1', null, '乐视网', null);
INSERT INTO `sys_user` VALUES ('203952574137905152', 'sadasd', '123456', '1528792549470', '1528792549470', '1', null, '1231', null);
INSERT INTO `sys_user` VALUES ('203952893215387648', 'sadasd1', '123456', '1528792624635', '1528792624635', '1', null, '1231', null);

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `parent_id` int(20) DEFAULT NULL,
  `parent_name` varchar(30) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', '管理员组', 'ManagerCode', null, null, '管理员用户组');

-- ----------------------------
-- Table structure for sys_user_group_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_pk`;
CREATE TABLE `sys_user_group_pk` (
  `group_id` int(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group_pk
-- ----------------------------
INSERT INTO `sys_user_group_pk` VALUES ('1', '196618686130565120');
INSERT INTO `sys_user_group_pk` VALUES ('1', '196618686130565121');

-- ----------------------------
-- Table structure for sys_user_group_role_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group_role_pk`;
CREATE TABLE `sys_user_group_role_pk` (
  `group_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group_role_pk
-- ----------------------------
INSERT INTO `sys_user_group_role_pk` VALUES ('1', '1');
INSERT INTO `sys_user_group_role_pk` VALUES ('1', '2');

-- ----------------------------
-- Table structure for sys_user_role_pk
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_pk`;
CREATE TABLE `sys_user_role_pk` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role_pk
-- ----------------------------
INSERT INTO `sys_user_role_pk` VALUES ('196618686130565120', '1');
INSERT INTO `sys_user_role_pk` VALUES ('4', '2');
INSERT INTO `sys_user_role_pk` VALUES ('4', '1');
