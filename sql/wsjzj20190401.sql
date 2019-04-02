/*
Navicat MySQL Data Transfer

Source Server         : localdb
Source Server Version : 50725
Source Host           : 127.0.0.1:3306
Source Database       : wsjzj

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-01 21:04:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bank_checkbill`
-- ----------------------------
DROP TABLE IF EXISTS `bank_checkbill`;
CREATE TABLE `bank_checkbill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(4) DEFAULT NULL COMMENT '系统交易码',
  `bank_code` varchar(5) DEFAULT NULL COMMENT '银行交易码',
  `check_date` varchar(8) DEFAULT NULL COMMENT '对账日期',
  `recharge_num` varchar(4) DEFAULT NULL COMMENT '充值总笔数',
  `recharge_sum` varchar(17) DEFAULT NULL COMMENT '充值总金额',
  `correction_num` varchar(4) DEFAULT NULL COMMENT '冲正总笔数',
  `correction_sum` varchar(17) DEFAULT NULL COMMENT '冲正总金额',
  `close_num` varchar(4) DEFAULT NULL COMMENT '销户总笔数',
  `close_sum` varchar(17) DEFAULT NULL COMMENT '销户总金额',
  `merchant_num` varchar(4) DEFAULT NULL COMMENT '商户总笔数',
  `merchant_sum` varchar(17) DEFAULT NULL COMMENT '商户总金额',
  `recharge_card_sum` varchar(17) DEFAULT NULL COMMENT '所有正确圈存的卡号总和',
  `correction_card_sum` varchar(17) DEFAULT NULL COMMENT '所有冲正的卡号总和',
  `close_card_sum` varchar(17) DEFAULT NULL COMMENT '所有销户的卡号总和',
  `merchant_card_sum` varchar(17) DEFAULT NULL COMMENT '所有结算的卡号总和',
  `return_code` varchar(6) DEFAULT NULL COMMENT '返回码',
  `return_message` varchar(34) DEFAULT NULL COMMENT '返回信息',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='银行总账对账表';

-- ----------------------------
-- Records of bank_checkbill
-- ----------------------------

-- ----------------------------
-- Table structure for `bank_checkdetail`
-- ----------------------------
DROP TABLE IF EXISTS `bank_checkdetail`;
CREATE TABLE `bank_checkdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(20) DEFAULT NULL COMMENT '编号',
  `idserial` varchar(32) DEFAULT NULL COMMENT '系统流水号',
  `bank_idserial` varchar(32) DEFAULT NULL COMMENT '银行流水号',
  `trans_date` varchar(8) DEFAULT NULL COMMENT '转账日期',
  `amount` varchar(17) DEFAULT NULL COMMENT '金额',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `check_status` char(1) DEFAULT NULL COMMENT '对账状态',
  `oper_user` bigint(20) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='银行详情对账对账表';

-- ----------------------------
-- Records of bank_checkdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `bank_transaction_record`
-- ----------------------------
DROP TABLE IF EXISTS `bank_transaction_record`;
CREATE TABLE `bank_transaction_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(4) DEFAULT NULL COMMENT '交易码',
  `bank_code` varchar(5) DEFAULT NULL COMMENT '银行交易码',
  `trans_date` varchar(8) DEFAULT NULL COMMENT '转账日期',
  `trans_idserial` varchar(32) DEFAULT NULL COMMENT '转账流水号',
  `user_code` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `bank_number` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `amount` varchar(17) DEFAULT NULL COMMENT '金额',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `return_code` varchar(6) DEFAULT NULL COMMENT '返回码',
  `return_message` varchar(34) DEFAULT NULL COMMENT '返回信息',
  `bank_idserial` varchar(32) DEFAULT NULL COMMENT '银行流水号',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='银行转账表';

-- ----------------------------
-- Records of bank_transaction_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_account`
-- ----------------------------
DROP TABLE IF EXISTS `bus_account`;
CREATE TABLE `bus_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `person_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `person_account` varchar(20) DEFAULT NULL COMMENT '账户号',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='用户账户表';

-- ----------------------------
-- Records of bus_account
-- ----------------------------
INSERT INTO `bus_account` VALUES ('1', '0', '1000000000', 'system', '2019-04-01 17:10:40');

-- ----------------------------
-- Table structure for `bus_closed_person`
-- ----------------------------
DROP TABLE IF EXISTS `bus_closed_person`;
CREATE TABLE `bus_closed_person` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `number` varchar(20) DEFAULT NULL COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `nation` bigint(20) DEFAULT NULL COMMENT '民族',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `bank_card_number` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `build` varchar(20) DEFAULT NULL COMMENT '楼号',
  `photo` varchar(100) DEFAULT '' COMMENT '照片',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `area` varchar(20) DEFAULT NULL COMMENT '监区',
  `room` varchar(20) DEFAULT NULL COMMENT '房间',
  `bed` varchar(20) DEFAULT NULL COMMENT '床号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门',
  `identity_id` bigint(20) DEFAULT NULL COMMENT '身份',
  `password` varchar(32) DEFAULT NULL COMMENT '密码（用于登录）',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐加密',
  `deposit` decimal(9,2) DEFAULT '0.00' COMMENT '押金',
  `balance` decimal(15,2) DEFAULT '0.00' COMMENT '账户余额',
  `already_cost` decimal(9,2) DEFAULT '0.00' COMMENT '当月已经消费',
  `flag` char(1) DEFAULT '0' COMMENT '账户状态',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销户人员管理';

-- ----------------------------
-- Records of bus_closed_person
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_device`
-- ----------------------------
DROP TABLE IF EXISTS `bus_device`;
CREATE TABLE `bus_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(4) DEFAULT NULL COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `address` varchar(30) DEFAULT NULL COMMENT '位置',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='设备信息';

-- ----------------------------
-- Records of bus_device
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_goods`
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `price` decimal(9,2) DEFAULT NULL COMMENT '价格',
  `order_num` int(4) DEFAULT NULL COMMENT '显示顺序',
  `image` varchar(100) DEFAULT NULL COMMENT '图片',
  `visible` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '分类状态',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of bus_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_good_category`
-- ----------------------------
DROP TABLE IF EXISTS `bus_good_category`;
CREATE TABLE `bus_good_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `ancestors` varchar(50) DEFAULT NULL COMMENT '分类编码',
  `category_name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `order_num` int(4) DEFAULT NULL COMMENT '显示顺序',
  `icon` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '分类图标',
  `visible` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '分类状态',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ----------------------------
-- Records of bus_good_category
-- ----------------------------
INSERT INTO `bus_good_category` VALUES ('100', '0', '0', '全部分类', '1', 'fa fa-bars', '1', '0', null, 'admin', '2019-03-14 21:51:51', 'admin', '2019-03-14 23:52:48');

-- ----------------------------
-- Table structure for `bus_identity`
-- ----------------------------
DROP TABLE IF EXISTS `bus_identity`;
CREATE TABLE `bus_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '身份编码',
  `name` varchar(100) DEFAULT NULL COMMENT '身份名称',
  `cost_total` decimal(9,2) DEFAULT NULL COMMENT '消费上限',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='身份管理';

-- ----------------------------
-- Records of bus_identity
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `bus_merchant`;
CREATE TABLE `bus_merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_code` varchar(10) DEFAULT NULL COMMENT '商户号',
  `merchant_name` varchar(30) NOT NULL COMMENT '商户名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `remark` varchar(50) DEFAULT NULL COMMENT '描述',
  `account_code` varchar(10) DEFAULT NULL COMMENT '账户编号',
  `account_name` varchar(30) DEFAULT NULL COMMENT '账号名称',
  `balance` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  `flag` char(1) CHARACTER SET utf8 DEFAULT '3' COMMENT '账户标识',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='商户表';

-- ----------------------------
-- Records of bus_merchant
-- ----------------------------
INSERT INTO `bus_merchant` VALUES ('1', '100000', '系统收入账户', '18201335133', null, '系统收入账户', null, null, '0.00', '1', '0', '1', '2018-08-07 12:58:21', 'test3', '2019-03-31 20:49:26');
INSERT INTO `bus_merchant` VALUES ('2', '200000', '系统支出账户', null, null, '系统支出账户', null, null, '0.00', '2', '0', '1', '2018-08-07 12:58:21', 'test3', '2019-03-31 20:45:36');
INSERT INTO `bus_merchant` VALUES ('3', '300000', '系统预支出账户', null, null, '系统预支出账户', null, null, '0.00', '0', '1', '1', '2018-11-11 14:57:51', 'admin', '2019-03-21 18:42:07');

-- ----------------------------
-- Table structure for `bus_merchant_report`
-- ----------------------------
DROP TABLE IF EXISTS `bus_merchant_report`;
CREATE TABLE `bus_merchant_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `account_code` varchar(10) DEFAULT NULL COMMENT '账户id',
  `income_num` int(11) DEFAULT NULL COMMENT '收入笔数',
  `income_sum` decimal(15,2) DEFAULT NULL COMMENT '收入金额',
  `outcome_num` int(11) DEFAULT NULL COMMENT '支出笔数',
  `outcome_sum` decimal(15,2) DEFAULT NULL COMMENT '支出金额',
  `remark` varchar(32) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8mb4 COMMENT='系统账户报表';

-- ----------------------------
-- Records of bus_merchant_report
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_modify_pwd`
-- ----------------------------
DROP TABLE IF EXISTS `bus_modify_pwd`;
CREATE TABLE `bus_modify_pwd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `number` varchar(255) NOT NULL COMMENT '编号',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `new_pwd` varchar(32) DEFAULT NULL COMMENT '新密码',
  `agreest` char(1) DEFAULT '0' COMMENT '是否同意',
  `remark` varchar(100) DEFAULT NULL COMMENT '说明',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='密码修改申请表';

-- ----------------------------
-- Records of bus_modify_pwd
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_oper_report`
-- ----------------------------
DROP TABLE IF EXISTS `bus_oper_report`;
CREATE TABLE `bus_oper_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `trade_code` varchar(4) DEFAULT NULL COMMENT '交易码',
  `trade_num` int(11) DEFAULT NULL COMMENT '交易笔数',
  `trade_sum` decimal(15,2) DEFAULT NULL COMMENT '交易金额',
  `remark` varchar(64) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=722 DEFAULT CHARSET=utf8mb4 COMMENT='系统操作报表';

-- ----------------------------
-- Records of bus_oper_report
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_order`
-- ----------------------------
DROP TABLE IF EXISTS `bus_order`;
CREATE TABLE `bus_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_code` varchar(32) NOT NULL COMMENT '订单号',
  `money` decimal(9,2) NOT NULL COMMENT '订单总金额',
  `person_id` bigint(20) NOT NULL COMMENT '购买人ID',
  `person_code` varchar(255) NOT NULL COMMENT '购买人编码',
  `person_name` varchar(255) NOT NULL COMMENT '购买人姓名',
  `flag` char(1) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `finish_user` varchar(100) DEFAULT NULL COMMENT '完成人员',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of bus_order
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bus_order_detail`;
CREATE TABLE `bus_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家id',
  `merchant_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `goods_name` char(255) NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `num` int(11) DEFAULT '0' COMMENT '数量',
  `money` decimal(9,2) DEFAULT '0.00' COMMENT '金额',
  `flag` char(1) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of bus_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_person`
-- ----------------------------
DROP TABLE IF EXISTS `bus_person`;
CREATE TABLE `bus_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(20) DEFAULT NULL COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `nation` char(2) DEFAULT NULL COMMENT '民族',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `bank_card_number` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `photo` varchar(100) DEFAULT '' COMMENT '照片',
  `build` varchar(20) DEFAULT NULL COMMENT '楼号',
  `area` varchar(20) DEFAULT NULL COMMENT '监区',
  `room` varchar(20) DEFAULT NULL COMMENT '房间',
  `bed` varchar(20) DEFAULT NULL COMMENT '床号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门',
  `identity_id` bigint(20) DEFAULT NULL COMMENT '身份',
  `password` varchar(32) DEFAULT NULL COMMENT '密码（用于登录）',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐加密',
  `deposit` decimal(9,2) DEFAULT '0.00' COMMENT '押金',
  `balance` decimal(15,2) DEFAULT '0.00' COMMENT '账户余额',
  `already_cost` decimal(9,2) DEFAULT '0.00' COMMENT '当月已经消费',
  `flag` char(1) DEFAULT '0' COMMENT '账户状态',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12498 DEFAULT CHARSET=utf8mb4 COMMENT='人员管理';

-- ----------------------------
-- Records of bus_person
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_settle_date`
-- ----------------------------
DROP TABLE IF EXISTS `bus_settle_date`;
CREATE TABLE `bus_settle_date` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `settle_date` date DEFAULT NULL COMMENT '结算日期',
  `settle_status` varchar(1) DEFAULT NULL COMMENT '结算状态',
  `status` varchar(1) DEFAULT NULL COMMENT '数据状态',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统结账日期表';

-- ----------------------------
-- Records of bus_settle_date
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_trade_record`
-- ----------------------------
DROP TABLE IF EXISTS `bus_trade_record`;
CREATE TABLE `bus_trade_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `journo` varchar(32) DEFAULT NULL COMMENT '流水号',
  `user_number` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `merchant_code` varchar(20) DEFAULT NULL COMMENT '商户编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单号',
  `txcode` varchar(4) DEFAULT NULL COMMENT '交易代码',
  `tx_before` decimal(15,2) DEFAULT NULL COMMENT '消费前余额',
  `tx_after` decimal(15,2) DEFAULT NULL COMMENT '消费后余额',
  `txamt` decimal(15,2) DEFAULT NULL COMMENT '交易金额',
  `from_acc` varchar(10) DEFAULT NULL COMMENT '来源账户',
  `to_acc` varchar(10) DEFAULT NULL COMMENT '目标账户',
  `reg_date` date DEFAULT NULL COMMENT '系统入账日期',
  `station_code` varchar(8) DEFAULT NULL COMMENT '设备编号',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18189 DEFAULT CHARSET=utf8mb4 COMMENT='流水表';

-- ----------------------------
-- Records of bus_trade_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bus_upload_record`
-- ----------------------------
DROP TABLE IF EXISTS `bus_upload_record`;
CREATE TABLE `bus_upload_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module` varchar(50) DEFAULT NULL COMMENT '功能模块名称',
  `upload_name` varchar(30) DEFAULT NULL COMMENT '上传文件名',
  `success_count` bigint(20) DEFAULT NULL COMMENT '成功条数',
  `fail_name` varchar(100) DEFAULT NULL COMMENT '失败文件名',
  `fail_count` bigint(20) DEFAULT NULL COMMENT '失败条数',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_by` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COMMENT='功能导入记录表';

-- ----------------------------
-- Records of bus_upload_record
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-05 20:16:24', '默认 skin-default、蓝色 skin-blue、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('100', '服务IP', 'bank.server.IP', '127.0.0.1', 'Y', 'admin', '2018-11-08 22:10:40', 'admin', '2018-11-08 22:14:59', '银行服务IP');
INSERT INTO `sys_config` VALUES ('101', '服务端口', 'bank.server.port', '15999', 'Y', 'admin', '2018-11-08 22:11:26', 'admin', '2019-03-29 21:04:33', '银行服务端口');
INSERT INTO `sys_config` VALUES ('102', '客户号', 'bank.server.CorpNo', '6665100029913688', 'Y', 'admin', '2018-11-08 22:13:40', 'admin', '2019-03-29 21:01:17', '银行分配的客户号');
INSERT INTO `sys_config` VALUES ('103', '省份代码', 'bank.server.DbProv', '30', 'Y', 'admin', '2018-11-08 22:14:38', 'admin', '2019-03-29 21:00:30', '省份代码');
INSERT INTO `sys_config` VALUES ('104', '币种', 'bank.server.DbCur', '01', 'Y', 'admin', '2018-11-10 14:19:00', 'admin', '2019-03-29 21:03:14', '币种');
INSERT INTO `sys_config` VALUES ('105', '操作员号', 'bank.server.OpNo', '0002', 'Y', 'admin', '2018-11-10 14:19:48', 'admin', '2019-03-29 21:03:40', '操作员号');
INSERT INTO `sys_config` VALUES ('106', '上级账簿号', 'bank.server.CrLogAccNo', '0000000000', 'Y', 'admin', '2018-11-10 14:20:34', 'admin', '2019-03-29 21:04:13', '上级账簿号');
INSERT INTO `sys_config` VALUES ('107', '账号', 'bank.server.DbAccNo', '009101040010578', 'Y', 'admin', '2018-11-10 14:22:35', 'admin', '2019-04-01 18:01:49', '账号');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '乌苏矫治局', '0', 'admin', '', '', '0', '0', 'admin', '2018-03-16 11:33:00', 'test3', '2019-03-30 22:45:31');

-- ----------------------------
-- Table structure for `sys_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('18', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('19', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('20', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('21', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('22', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('23', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('24', '8', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('25', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('26', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('100', '1', '默认', '1', 'bus_device_type', '', 'default', 'Y', '0', 'admin', '2018-11-03 18:42:22', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('101', '2', '触屏', '2', 'bus_device_type', '', 'default', 'Y', '0', 'admin', '2018-11-03 18:42:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('106', '1', '待审核', '0', 'bus_modify_pwd', '', '', 'Y', '0', 'admin', '2018-08-07 14:35:31', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('107', '2', '审核通过', '1', 'bus_modify_pwd', '', '', 'Y', '0', 'admin', '2018-08-07 14:37:23', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('108', '3', '驳回', '2', 'bus_modify_pwd', '', '', 'Y', '0', 'admin', '2018-08-07 14:37:54', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('109', '1', '显示', '1', 'bus_show_hide', '', '', 'Y', '0', 'admin', '2018-08-08 11:35:11', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('110', '2', '隐藏', '0', 'bus_show_hide', '', '', 'Y', '0', 'admin', '2018-08-08 11:35:30', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('111', '0', '正常', '0', 'bus_person_flag', '', '', 'Y', '0', 'admin', '2018-08-09 18:14:50', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('112', '1', '冻结', '1', 'bus_person_flag', '', '', 'Y', '0', 'admin', '2018-08-09 18:15:45', 'admin', '2018-08-09 18:16:14', '');
INSERT INTO `sys_dict_data` VALUES ('113', '2', '预销户', '2', 'bus_person_flag', '', '', 'Y', '0', 'admin', '2018-08-09 18:16:00', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('114', '3', '销户', '3', 'bus_person_flag', '', '', 'Y', '0', 'admin', '2018-08-09 18:16:37', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('115', '0', '汉族', '1', 'bus_person_nation', '', '', 'Y', '0', 'admin', '2018-08-10 00:13:04', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('116', '1', '壮族', '2', 'bus_person_nation', '', '', 'Y', '0', 'admin', '2018-08-10 00:15:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('117', '2', '回族', '3', 'bus_person_nation', '', '', 'N', '0', 'admin', '2018-08-10 00:15:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('118', '3', '满族', '4', 'bus_person_nation', '', '', 'N', '0', 'admin', '2018-08-10 00:15:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('119', '4', '维吾尔族', '5', 'bus_person_nation', '', '', 'N', '0', 'admin', '2018-08-10 00:15:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('120', '5', '苗族', '6', 'bus_person_nation', '', '', 'N', '0', 'admin', '2018-08-10 00:15:41', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('121', '99', '其他', '99', 'bus_person_nation', '', '', 'Y', '0', 'admin', '2018-08-10 00:31:42', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('122', '0', '配送中', '0', 'bus_order_flag', '', '', 'Y', '0', 'admin', '2018-08-10 15:46:04', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('123', '1', '完成', '1', 'bus_order_flag', '', '', 'Y', '0', 'admin', '2018-08-10 15:46:28', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('124', '2', '取消', '2', 'bus_order_flag', '', '', 'Y', '0', 'admin', '2018-08-10 15:46:40', '', null, '');
INSERT INTO `sys_dict_data` VALUES ('125', '1', '现金充值', '1000', 'bus_txcode', '', 'default', 'Y', '0', 'admin', '2019-03-20 15:00:12', 'admin', '2019-03-23 14:03:28', '现金充值');
INSERT INTO `sys_dict_data` VALUES ('126', '2', '批量充值', '1001', 'bus_txcode', '', 'default', 'Y', '0', 'admin', '2019-03-20 15:01:08', 'admin', '2019-04-01 20:53:37', '导入充值');
INSERT INTO `sys_dict_data` VALUES ('127', '3', '批量转账', '1002', 'bus_txcode', '', 'default', 'Y', '0', 'admin', '2019-03-20 15:01:30', 'admin', '2019-03-23 14:03:52', '银行批量转账');
INSERT INTO `sys_dict_data` VALUES ('128', '4', '单笔转账', '1003', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:04:18', '', null, '银行单笔转账');
INSERT INTO `sys_dict_data` VALUES ('129', '5', '开户押金', '1004', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:04:51', '', null, '开户押金流水');
INSERT INTO `sys_dict_data` VALUES ('130', '6', '购买商品', '2000', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:05:12', '', null, '购买商品');
INSERT INTO `sys_dict_data` VALUES ('131', '7', '批量消费', '2001', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:05:53', 'admin', '2019-04-01 20:53:49', '批量导入消费');
INSERT INTO `sys_dict_data` VALUES ('132', '8', '商品退款', '3000', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:06:18', '', null, '商品退款');
INSERT INTO `sys_dict_data` VALUES ('133', '9', '销户退款', '3001', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:06:40', '', null, '销户退换押金和余额');
INSERT INTO `sys_dict_data` VALUES ('134', '10', '商户结算', '4000', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-03-23 14:07:04', '', null, '商户结算');
INSERT INTO `sys_dict_data` VALUES ('135', '11', '现金扣款', '1005', 'bus_txcode', '', '', 'Y', '0', 'admin', '2019-04-01 20:56:16', '', null, '现金扣款');

-- ----------------------------
-- Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('8', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('9', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES ('100', '设备类型', 'bus_device_type', '0', 'admin', '2018-11-03 18:41:26', '', null, '设备类型');
INSERT INTO `sys_dict_type` VALUES ('103', '密码审核状态', 'bus_modify_pwd', '0', 'admin', '2018-08-07 14:33:37', 'admin', '2018-08-08 11:36:34', '密码审核状态');
INSERT INTO `sys_dict_type` VALUES ('104', '商品显隐状态', 'bus_show_hide', '0', 'admin', '2018-08-08 11:34:33', 'admin', '2018-08-08 11:36:22', '商品显隐状态');
INSERT INTO `sys_dict_type` VALUES ('105', '人员状态', 'bus_person_flag', '0', 'admin', '2018-08-09 18:13:55', '', null, '人员状态');
INSERT INTO `sys_dict_type` VALUES ('106', '民族', 'bus_person_nation', '0', 'admin', '2018-08-10 00:12:31', 'admin', '2018-08-10 00:12:40', '民族');
INSERT INTO `sys_dict_type` VALUES ('107', '订单状态', 'bus_order_flag', '0', 'admin', '2018-08-10 15:45:34', '', null, '订单状态');
INSERT INTO `sys_dict_type` VALUES ('108', '交易代码', 'bus_txcode', '0', 'admin', '2019-03-20 14:59:14', 'admin', '2019-03-20 14:59:23', '交易代码');

-- ----------------------------
-- Table structure for `sys_job`
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(200) DEFAULT '' COMMENT '方法参数',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '1' COMMENT '计划执行错误策略（1继续 2等待 3放弃）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', 'ryTask', '自动结账', 'settle', '', '0 10 0 * * ? *', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-27 00:24:17', '');
INSERT INTO `sys_job` VALUES ('2', 'ryTask', '历史结账', 'settleHistoryData', '2019-03-17', '0/20 * * * * ?', '2', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-03-19 18:36:05', '');
INSERT INTO `sys_job` VALUES ('100', 'ryTask', '消费限额归零', 'clearAlreadyCost', '', '0 10 0 * * ? *', '1', '1', 'admin', '2019-03-28 23:40:36', 'admin', '2019-03-28 23:47:23', '');
INSERT INTO `sys_job` VALUES ('101', 'ryTask', '充值查询', 'batchQueryForRecharge', '20190401', '0 10 0 * * ? *', '1', '1', 'admin', '2019-04-01 19:26:34', 'admin', '2019-04-01 19:27:35', '充值查询');

-- ----------------------------
-- Table structure for `sys_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(200) DEFAULT '' COMMENT '方法参数',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` text COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=584 DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_logininfor`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
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

-- ----------------------------
-- Table structure for `sys_notice`
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(2) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(500) NOT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(512) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo1', '董事长1', '1', '0', 'admin', '2018-03-16 11:33:00', 'test3', '2019-03-30 22:45:48', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级系统管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2019-03-30 19:46:00', '管理员');

-- ----------------------------
-- Table structure for `sys_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=777781 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', 'admin', '00', 'ry@163.com', '15888888888', '1', '', '4dc2909283aa9175f464830ec237a944', 'acf191', '0', '0', '192.168.2.168', '2019-04-01 20:52:55', 'admin', '2018-03-16 11:33:00', 'ry', '2019-04-01 20:52:55', '管理员');

-- ----------------------------
-- Table structure for `sys_user_online`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
