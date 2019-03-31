/*
Navicat MySQL Data Transfer

Source Server         : localdb
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-31 11:26:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2179 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-21 16:39:41', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('4', '用户管理', '0', '4', '#', 'M', '0', '', 'fa fa-address-book', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-28 21:53:44', '用户管理目录');
INSERT INTO `sys_menu` VALUES ('5', '商品管理', '0', '5', '#', 'M', '0', '', 'fa fa-barcode', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-28 21:54:08', '商品管理目录');
INSERT INTO `sys_menu` VALUES ('6', '日常业务', '0', '6', '#', 'M', '0', '', 'fa fa-calendar', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-28 21:54:18', '日常业务目录');
INSERT INTO `sys_menu` VALUES ('7', '批量业务', '0', '7', '#', 'M', '0', '', 'fa fa-group', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-28 21:55:00', '批量业务目录');
INSERT INTO `sys_menu` VALUES ('8', '报表管理', '0', '8', '#', 'M', '0', '', 'fa fa-language', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-28 21:55:47', '报表管理目录');
INSERT INTO `sys_menu` VALUES ('100', '操作员管理', '1', '1', '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '表单构建', '3', '1', '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('113', '代码生成', '3', '2', '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('114', '系统接口', '3', '3', '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '重置密码', '100', '5', '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '角色查询', '101', '1', '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色新增', '101', '2', '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色修改', '101', '3', '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色删除', '101', '4', '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色导出', '101', '4', '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '菜单查询', '102', '1', '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单新增', '102', '2', '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单修改', '102', '3', '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单删除', '102', '4', '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '部门查询', '103', '1', '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门新增', '103', '2', '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门修改', '103', '3', '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门删除', '103', '4', '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '岗位查询', '104', '1', '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位新增', '104', '2', '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位修改', '104', '3', '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位删除', '104', '4', '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位导出', '104', '4', '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '字典查询', '105', '1', '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典新增', '105', '2', '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典修改', '105', '3', '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典删除', '105', '4', '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典导出', '105', '4', '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '参数查询', '106', '1', '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数新增', '106', '2', '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数修改', '106', '3', '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数删除', '106', '4', '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数导出', '106', '4', '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '公告查询', '107', '1', '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告新增', '107', '2', '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告修改', '107', '3', '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告删除', '107', '4', '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '操作查询', '500', '1', '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作删除', '500', '2', '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '详细信息', '500', '3', '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '日志导出', '500', '3', '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '登录查询', '501', '1', '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录删除', '501', '2', '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '日志导出', '501', '2', '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '在线查询', '109', '1', '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '批量强退', '109', '2', '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '单条强退', '109', '3', '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '任务查询', '110', '1', '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务新增', '110', '2', '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务修改', '110', '3', '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务删除', '110', '4', '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '状态修改', '110', '5', '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '任务导出', '110', '5', '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '生成查询', '113', '1', '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '生成代码', '113', '2', '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2086', '充值', '6', '1', '/business/singleRecharge', 'C', '0', 'business:singleRecharge:view', '#', 'admin', '2018-10-30 14:19:32', '', null, '');
INSERT INTO `sys_menu` VALUES ('2087', '商户报表', '8', '2', '/business/merchantReport', 'C', '0', 'business:merchantReport:view', '#', 'admin', '2018-10-30 14:21:37', 'admin', '2019-03-18 20:08:31', '');
INSERT INTO `sys_menu` VALUES ('2088', '报表查询', '2087', '1', '#', 'F', '0', 'business:merchantReport:list', '#', 'admin', '2018-10-30 14:22:29', 'admin', '2019-03-29 10:21:54', '');
INSERT INTO `sys_menu` VALUES ('2089', '账单对账', '8', '3', '/business/checkbill', 'C', '1', 'business:checkbill:view', '#', 'admin', '2018-10-30 14:23:33', 'admin', '2019-03-12 18:18:57', '');
INSERT INTO `sys_menu` VALUES ('2090', '账单查询', '2089', '1', '#', 'F', '0', 'business:checkbill:list', '#', 'admin', '2018-10-30 14:23:54', 'admin', '2018-10-30 14:24:32', '');
INSERT INTO `sys_menu` VALUES ('2091', '详情对账', '8', '4', '/business/checkdetail', 'C', '1', 'business:checkdetail:view', '#', 'admin', '2018-10-30 14:25:10', 'admin', '2019-03-12 18:19:13', '');
INSERT INTO `sys_menu` VALUES ('2092', '详情查询', '2091', '5', '#', 'F', '0', 'business:checkdetail:list', '#', 'admin', '2018-10-30 14:25:41', '', null, '');
INSERT INTO `sys_menu` VALUES ('2093', '商品分类维护', '5', '6', '/business/goodCategory', 'C', '0', 'business:goodCategory:view', '#', 'admin', '2018-10-30 14:26:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2094', '分类查询', '2093', '1', '#', 'F', '0', 'business:goodCategory:list', '#', 'admin', '2018-10-30 14:27:08', '', null, '');
INSERT INTO `sys_menu` VALUES ('2095', '分类新增', '2093', '2', '#', 'F', '0', 'business:goodCategory:add', '#', 'admin', '2018-10-30 14:28:04', '', null, '');
INSERT INTO `sys_menu` VALUES ('2096', '分类修改', '2093', '3', '#', 'F', '0', 'business:goodCategory:edit', '#', 'admin', '2018-10-30 14:28:35', '', null, '');
INSERT INTO `sys_menu` VALUES ('2097', '分类删除', '2093', '4', '#', 'F', '0', 'business:goodCategory:remove', '#', 'admin', '2018-10-30 14:29:01', '', null, '');
INSERT INTO `sys_menu` VALUES ('2098', '商品信息维护', '5', '7', '/business/goods', 'C', '0', 'business:goods:view', '#', 'admin', '2018-10-30 14:29:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2099', '商品查询', '2098', '1', '#', 'F', '0', 'business:goods:list', '#', 'admin', '2018-10-30 14:30:03', '', null, '');
INSERT INTO `sys_menu` VALUES ('2100', '商品添加', '2098', '2', '#', 'F', '0', 'business:goods:add', '#', 'admin', '2018-10-30 14:30:22', '', null, '');
INSERT INTO `sys_menu` VALUES ('2101', '商品修改', '2098', '3', '#', 'F', '0', 'business:goods:edit', '#', 'admin', '2018-10-30 14:30:47', '', null, '');
INSERT INTO `sys_menu` VALUES ('2102', '商品删除', '2098', '4', '#', 'F', '0', 'business:goods:remove', '#', 'admin', '2018-10-30 14:31:06', '', null, '');
INSERT INTO `sys_menu` VALUES ('2103', '操作报表', '8', '8', '/business/operReport', 'C', '0', 'business:operReport:view', '#', 'admin', '2018-10-30 14:31:51', '', null, '');
INSERT INTO `sys_menu` VALUES ('2104', '操作查询', '2103', '1', '#', 'F', '0', 'business:operReport:list', '#', 'admin', '2018-10-30 14:32:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2105', '订单管理', '6', '9', '/business/order', 'C', '0', 'business:order:view', '#', 'admin', '2018-10-30 14:32:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2106', '订单查询', '2105', '1', '#', 'F', '0', 'business:order:list', '#', 'admin', '2018-10-30 14:33:13', '', null, '');
INSERT INTO `sys_menu` VALUES ('2107', '订单修改', '2105', '2', '#', 'F', '0', 'business:order:edit', '#', 'admin', '2018-10-30 14:34:11', '', null, '');
INSERT INTO `sys_menu` VALUES ('2108', '订单取消', '2105', '3', '#', 'F', '0', 'business:order:cancel', '#', 'admin', '2018-10-30 14:34:36', '', null, '');
INSERT INTO `sys_menu` VALUES ('2109', '订单完成', '2105', '4', '#', 'F', '0', 'business:order:finish', '#', 'admin', '2018-10-30 14:34:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2113', '人员管理', '4', '11', '/business/person', 'C', '0', 'business:person:view', '#', 'admin', '2018-10-30 14:38:03', '', null, '');
INSERT INTO `sys_menu` VALUES ('2114', '人员查询', '2113', '1', '#', 'F', '0', 'business:person:list', '#', 'admin', '2018-10-30 14:38:19', '', null, '');
INSERT INTO `sys_menu` VALUES ('2115', '人员添加', '2113', '2', '#', 'F', '0', 'business:person:add', '#', 'admin', '2018-10-30 14:38:58', '', null, '');
INSERT INTO `sys_menu` VALUES ('2116', '人员修改', '2113', '3', '#', 'F', '0', 'business:person:edit', '#', 'admin', '2018-10-30 14:39:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2117', '人员删除', '2113', '4', '#', 'F', '0', 'business:person:remove', '#', 'admin', '2018-10-30 14:39:37', '', null, '');
INSERT INTO `sys_menu` VALUES ('2118', '结账日期', '1', '12', '/business/settleDate', 'C', '1', 'business:settleDate:view', '#', 'admin', '2018-10-30 14:40:09', 'admin', '2019-03-17 16:14:22', '');
INSERT INTO `sys_menu` VALUES ('2119', '日期查询', '2118', '1', '#', 'F', '0', 'business:settleDate:list', '#', 'admin', '2018-10-30 14:40:32', '', null, '');
INSERT INTO `sys_menu` VALUES ('2120', '流水查询', '6', '13', '/business/tradeRecord', 'C', '0', 'business:tradeRecord:view', '#', 'admin', '2018-10-30 14:41:18', '', null, '');
INSERT INTO `sys_menu` VALUES ('2121', '流水查询', '2120', '1', '#', 'F', '0', 'business:tradeRecord:list', '#', 'admin', '2018-10-30 14:41:37', '', null, '');
INSERT INTO `sys_menu` VALUES ('2122', '银行转账', '6', '14', '/business/transactionRecord', 'C', '1', 'business:transactionRecord:view', '#', 'admin', '2018-10-30 14:42:44', 'admin', '2019-03-06 18:17:26', '');
INSERT INTO `sys_menu` VALUES ('2123', '记录查询', '2122', '1', '#', 'F', '0', 'business:transactionRecord:list', '#', 'admin', '2018-10-30 14:42:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2124', '批量消费', '7', '15', '/business/batchCost', 'C', '0', 'business:batchCost:view', '#', 'admin', '2018-10-30 14:43:35', '', null, '');
INSERT INTO `sys_menu` VALUES ('2125', '消费查询', '2124', '1', '#', 'F', '0', 'business:batchCost:list', '#', 'admin', '2018-10-30 14:43:56', '', null, '');
INSERT INTO `sys_menu` VALUES ('2126', '批量充值', '7', '16', '/business/uploadRecord', 'C', '0', 'business:uploadRecord:view', '#', 'admin', '2018-10-30 14:45:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('2127', '导入查询', '2126', '1', '#', 'F', '0', 'business:uploadRecord:list', '#', 'admin', '2018-10-30 14:45:19', '', null, '');
INSERT INTO `sys_menu` VALUES ('2128', '设备管理', '1', '17', '/system/device', 'C', '0', 'system:device:view', '#', 'admin', '2018-10-30 14:46:17', '', null, '');
INSERT INTO `sys_menu` VALUES ('2129', '设备查询', '2128', '1', '#', 'F', '0', 'system:device:list', '#', 'admin', '2018-10-30 14:46:47', '', null, '');
INSERT INTO `sys_menu` VALUES ('2130', '设备新增', '2128', '2', '#', 'F', '0', 'system:device:add', '#', 'admin', '2018-10-30 14:47:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2131', '设备修改', '2128', '3', '#', 'F', '0', 'system:device:edit', '#', 'admin', '2018-10-30 14:47:17', '', null, '');
INSERT INTO `sys_menu` VALUES ('2132', '设备删除', '2128', '4', '#', 'F', '0', 'system:device:remove', '#', 'admin', '2018-10-30 14:47:31', '', null, '');
INSERT INTO `sys_menu` VALUES ('2133', '商户管理', '1', '18', '/system/merchant', 'C', '0', 'system:merchant:view', '#', 'admin', '2018-10-30 14:48:16', '', null, '');
INSERT INTO `sys_menu` VALUES ('2134', '商户查询', '2133', '1', '#', 'F', '0', 'system:merchant:list', '#', 'admin', '2018-10-30 14:48:34', '', null, '');
INSERT INTO `sys_menu` VALUES ('2135', '商户添加', '2133', '2', '#', 'F', '0', 'system:merchant:add', '#', 'admin', '2018-10-30 14:48:53', '', null, '');
INSERT INTO `sys_menu` VALUES ('2136', '商户修改', '2133', '3', '#', 'F', '0', 'system:merchant:edit', '#', 'admin', '2018-10-30 14:49:10', '', null, '');
INSERT INTO `sys_menu` VALUES ('2137', '商户删除', '2133', '4', '#', 'F', '0', 'system:merchant:remove', '#', 'admin', '2018-10-30 14:49:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2138', '密码审核', '4', '19', '/system/modifyPwd', 'C', '0', 'system:modifyPwd:view', '#', 'admin', '2018-10-30 14:50:13', 'admin', '2019-03-24 16:17:12', '');
INSERT INTO `sys_menu` VALUES ('2139', '密码查询', '2138', '1', '#', 'F', '0', 'system:modifyPwd:list', '#', 'admin', '2018-10-30 14:50:31', '', null, '');
INSERT INTO `sys_menu` VALUES ('2140', '通过申请', '2138', '2', '#', 'F', '0', 'system:modifyPwd:review', '#', 'admin', '2018-10-30 14:50:57', '', null, '');
INSERT INTO `sys_menu` VALUES ('2141', '身份管理', '1', '20', '/system/identity', 'C', '0', 'system:identity:view', '#', 'admin', '2018-11-03 19:19:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2142', '身份查询', '2141', '1', '#', 'F', '0', 'system:identity:list', '#', 'admin', '2018-11-03 19:19:36', '', null, '');
INSERT INTO `sys_menu` VALUES ('2143', '增加身份', '2141', '2', '#', 'F', '0', 'system:identity:add', '#', 'admin', '2018-11-03 19:20:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2144', '修改身份', '2141', '3', '#', 'F', '0', 'system:identity:edit', '#', 'admin', '2018-11-03 19:20:23', '', null, '');
INSERT INTO `sys_menu` VALUES ('2145', '删除身份', '2141', '4', '#', 'F', '0', 'system:identity:remove', '#', 'admin', '2018-11-03 19:20:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2146', '销户管理', '4', '21', '/business/closeAccount', 'C', '0', 'business:closeAccount:view', '#', 'admin', '2018-11-11 16:25:29', 'admin', '2019-03-29 09:31:25', '');
INSERT INTO `sys_menu` VALUES ('2147', '可销户查询', '2146', '1', '#', 'F', '0', 'business:closeAccount:list', '#', 'admin', '2018-11-11 16:26:06', 'admin', '2019-03-29 09:32:02', '');
INSERT INTO `sys_menu` VALUES ('2148', '销户查询', '4', '22', '/business/closedPerson', 'C', '0', 'business:closedPerson:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2018-11-18 17:12:34', '销户人员管理菜单');
INSERT INTO `sys_menu` VALUES ('2149', '销户人员查询', '2148', '1', '#', 'F', '0', 'business:closedPerson:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2150', '销户人员新增', '2148', '2', '#', 'F', '0', 'business:closedPerson:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2151', '销户人员修改', '2148', '3', '#', 'F', '0', 'business:closedPerson:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2152', '销户人员删除', '2148', '4', '#', 'F', '0', 'business:closedPerson:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2153', '银行开户', '2156', '23', '/business/openAccount', 'C', '0', 'business:openAccount:view', '#', 'admin', '2019-03-26 12:36:09', 'admin', '2019-03-28 23:48:26', '');
INSERT INTO `sys_menu` VALUES ('2154', '开户查询', '2153', '1', '#', 'F', '0', 'business:openAccount:list', '#', 'admin', '2019-03-26 12:36:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2155', '开户操作', '2153', '2', '#', 'F', '0', 'business:openAccount:openAccount', '#', 'admin', '2019-03-26 12:37:22', '', null, '');
INSERT INTO `sys_menu` VALUES ('2156', '银行管理', '0', '9', '#', 'M', '0', '', 'fa fa-credit-card-alt', 'admin', '2019-03-28 21:52:52', 'admin', '2019-03-28 21:57:29', '');
INSERT INTO `sys_menu` VALUES ('2157', '转账查询', '2156', '1', '/business/transactionRecord', 'C', '0', 'business:transactionRecord:view', '#', 'admin', '2019-03-28 21:57:53', 'admin', '2019-03-28 21:59:25', '');
INSERT INTO `sys_menu` VALUES ('2158', '记录查询', '2157', '1', '#', 'F', '0', 'business:transactionRecord:list', '#', 'admin', '2019-03-28 21:58:53', '', null, '');
INSERT INTO `sys_menu` VALUES ('2159', '生成销户单', '2146', '2', '#', 'F', '0', 'business:closeAccount:createPDF', '#', 'admin', '2019-03-30 15:33:47', '', null, '');
INSERT INTO `sys_menu` VALUES ('2160', '确认销户', '2146', '3', '#', 'F', '0', 'business:closeAccount:close', '#', 'admin', '2019-03-30 15:34:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2161', '商品信息导出', '2098', '5', '#', 'F', '0', 'business:goods:export', '#', 'admin', '2019-03-30 15:35:33', '', null, '');
INSERT INTO `sys_menu` VALUES ('2162', '商品信息导入', '2098', '6', '#', 'F', '0', 'business:goods:import', '#', 'admin', '2019-03-30 15:36:09', '', null, '');
INSERT INTO `sys_menu` VALUES ('2163', '订单导出', '2105', '5', '#', 'F', '0', 'business:order:export', '#', 'admin', '2019-03-30 15:37:21', '', null, '');
INSERT INTO `sys_menu` VALUES ('2164', '订单详情导出', '2105', '6', '#', 'F', '0', 'business:order:exportDetail', '#', 'admin', '2019-03-30 15:37:48', '', null, '');
INSERT INTO `sys_menu` VALUES ('2165', '流水导出', '2120', '2', '#', 'F', '0', 'business:tradeRecord:export', '#', 'admin', '2019-03-30 15:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2166', '批量消费导入', '2124', '2', '#', 'F', '0', 'business:batchCost:import', '#', 'admin', '2019-03-30 15:39:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2167', '批量消费导出', '2124', '3', '#', 'F', '0', 'business:batchCost:export', '#', 'admin', '2019-03-30 15:40:06', '', null, '');
INSERT INTO `sys_menu` VALUES ('2168', '批量充值导入', '2126', '2', '#', 'F', '0', 'business:uploadRecord:import', '#', 'admin', '2019-03-30 15:41:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('2169', '批量充值导出', '2126', '3', '#', 'F', '0', 'business:uploadRecord:export', '#', 'admin', '2019-03-30 15:41:20', '', null, '');
INSERT INTO `sys_menu` VALUES ('2170', '报表导出', '2087', '2', '#', 'F', '0', 'business:merchantReport:export', '#', 'admin', '2019-03-30 15:42:30', '', null, '');
INSERT INTO `sys_menu` VALUES ('2171', '报表导出', '2103', '2', '#', 'F', '0', 'business:operReport:export', '#', 'admin', '2019-03-30 15:43:01', 'admin', '2019-03-30 15:44:22', '');
INSERT INTO `sys_menu` VALUES ('2172', '转账记录导出', '2157', '2', '#', 'F', '0', 'business:transactionRecord:export', '#', 'admin', '2019-03-30 15:44:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2173', '预销户确认', '2113', '5', '#', 'F', '0', 'business:person:editFlag', '#', 'admin', '2019-03-30 21:41:44', '', null, '');
INSERT INTO `sys_menu` VALUES ('2174', '销户查询导出', '2148', '5', '#', 'F', '0', 'business:person:export', '#', 'admin', '2019-03-30 21:43:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2175', '人员导入', '2113', '6', '#', 'F', '0', 'business:person:import', '#', 'admin', '2019-03-31 09:44:05', '', null, '');
INSERT INTO `sys_menu` VALUES ('2176', '人员导出', '2113', '7', '#', 'F', '0', 'business:person:export', '#', 'admin', '2019-03-31 09:44:48', '', null, '');
INSERT INTO `sys_menu` VALUES ('2177', '商品上架/下架确认', '2098', '7', '#', 'F', '0', 'business:goods:editVisible', '#', 'admin', '2019-03-31 09:48:44', '', null, '');
INSERT INTO `sys_menu` VALUES ('2178', '详情查询', '2105', '7', '#', 'F', '0', 'business:orderDetail:list', '#', 'admin', '2019-03-31 09:50:41', '', null, '');
