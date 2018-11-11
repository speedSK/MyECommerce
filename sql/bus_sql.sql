/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/8/1 10:54:31                            */
/*==============================================================*/


drop table if exists bank_checkbill;

drop table if exists bank_checkdetail;

drop table if exists bank_recharge_record;

drop table if exists bus_account;

drop table if exists bus_account_report;

drop table if exists bus_cost_total;

drop table if exists bus_device;

drop table if exists bus_good_category;

drop table if exists bus_goods;

drop table if exists bus_merchant;

drop table if exists bus_modify_pwd;

drop table if exists bus_oper_report;

drop table if exists bus_order;

drop table if exists bus_order_detail;

drop table if exists bus_trade_record;

drop table if exists bus_user;

/*==============================================================*/
/* Table: bank_checkbill                                        */
/*==============================================================*/
create table bank_checkbill
(
   id                   bigint not null auto_increment comment '主键',
   code                 varchar(4) comment '系统交易码',
   bank_code            varchar(5) comment '银行交易码',
   check_date           varchar(8) comment '对账日期',
   recharge_num         varchar(4) comment '充值总笔数',
   recharge_sum         varchar(17) comment '充值总金额',
   correction_num       varchar(4) comment '冲正总笔数',
   correction_sum       varchar(17) comment '冲正总金额',
   close_num            varchar(4) comment '销户总笔数',
   close_sum            varchar(17) comment '销户总金额',
   merchant_num         varchar(4) comment '商户总笔数',
   merchant_sum         varchar(17) comment '商户总金额',
   recharge_card_sum    varchar(17) comment '所有正确圈存的卡号总和',
   correction_card_sum  varchar(17) comment '所有冲正的卡号总和',
   close_card_sum       varchar(17) comment '所有销户的卡号总和',
   merchant_card_sum    varchar(17) comment '所有结算的卡号总和',
   return_code          varchar(6) comment '返回码',
   return_message       varchar(34) comment '返回信息',
   status               char(1) comment '状态',
   create_time          datetime comment '创建时间戳',
   primary key (id)
);

/*==============================================================*/
/* Table: bank_checkdetail                                      */
/*==============================================================*/
create table bank_checkdetail
(
   id                   bigint not null auto_increment comment '主键',
   idserial             varchar(32) comment '系统流水号',
   bank_idserial        varchar(32) comment '银行流水号',
   trans_date           varchar(8) comment '转账日期',
   amount               varchar(17) comment '金额',
   check_status         char(1) comment '对账状态',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '时间戳',
   primary key (id)
);

/*==============================================================*/
/* Table: bank_recharge_record                                  */
/*==============================================================*/
create table bank_recharge_record
(
   id                   bigint not null auto_increment comment '主键',
   code                 varchar(4) comment '交易码',
   bank_code            varchar(5) comment '银行交易码',
   trans_date           varchar(8) comment '转账日期',
   trans_idserial       varchar(32) comment '转账流水号',
   user_code            varchar(20) comment '用户编号',
   user_name            varchar(20) comment '用户姓名',
   di_number            varchar(18) comment '身份证号',
   bank_number          varchar(32) comment '银行卡号',
   amount               varchar(17) comment '金额',
   status               char(1) comment '状态',
   return_code          varchar(6) comment '返回码',
   return_message       varchar(34) comment '返回信息',
   bank_idserial        varchar(32) comment '银行流水号',
   create_time          datetime comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_account                                           */
/*==============================================================*/
create table bus_account
(
   id                   bigint not null auto_increment comment '主键',
   account_code         varchar(10) comment '账户号',
   account_name         varchar(30) comment '账户名称',
   balance              decimal(15,2) comment '账户余额',
   flag                 char(1) comment '账户标识',
   status               char(1) comment '状态',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_account_report                                    */
/*==============================================================*/
create table bus_account_report
(
   id                   bigint not null auto_increment,
   report_date          varchar(10) comment '报表日期',
   account_id           bigint comment '账户id',
   income_num           int comment '收入笔数',
   income_sum           decimal(15,2) comment '收入金额',
   outcome_num          int comment '支出笔数',
   outcome_sum          decimal(15,2) comment '支出金额',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_cost_total                                        */
/*==============================================================*/
create table bus_cost_total
(
   id                   bigint not null auto_increment comment '主键',
   p_id                 bigint comment '身份id',
   cost_total           decimal(9,2) comment '消费上限',
   opser_user           bigint comment '操作人',
   status               char(1) comment '状态',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '时间戳',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_device                                            */
/*==============================================================*/
create table bus_device
(
   id                   bigint not null auto_increment comment '主键',
   code                 varchar(4) comment '编号',
   name                 varchar(20) comment '名称',
   type                 char(1) comment '类型',
   ip                   varchar(15) comment 'IP地址',
   address              varchar(30) comment '位置',
   des                  varchar(50) comment '说明',
   oper_user            bigint comment '操作用户',
   status               char(1) comment '状态',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_good_category                                     */
/*==============================================================*/
create table bus_good_category
(
   id                   bigint not null auto_increment comment '主键',
   category_code        varchar(10) comment '分类编码',
   category_name        varchar(20) comment '分类名称',
   parent_id            bigint comment '父级id',
   status               char(1) comment '状态',
   des                  varchar(50) comment '描述',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_goods                                             */
/*==============================================================*/
create table bus_goods
(
   id                   bigint not null auto_increment comment '主键',
   code                 varchar(20) comment '编码',
   name                 varchar(30) comment '名称',
   price                decimal(9,2) comment '价格',
   image                varchar(30) comment '图片',
   merchant_id          bigint comment '商户id',
   des                  varchar(50) comment '描述',
   gooods_status        char(1) comment '商品上架状态',
   status               char(1) comment '状态',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_merchant                                          */
/*==============================================================*/
create table bus_merchant
(
   id                   bigint not null auto_increment comment '主键',
   merchant_code        varchar(10) comment '商户号',
   merchant_name        varchar(30) comment '商户名称',
   account_id           bigint comment '账户id',
   des                  varchar(50) comment '描述',
   mobile               varchar(11) comment '联系方式',
   address              varchar(50) comment '地址',
   status               char(1) comment '状态',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_modify_pwd                                        */
/*==============================================================*/
create table bus_modify_pwd
(
   id                   bigint not null auto_increment comment '主键',
   userid               bigint comment '用户id',
   new_pwd              varchar(32) comment '新秘密',
   agreest              char(1) comment '是否同意',
   status               char(1) comment '状态',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_oper_report                                       */
/*==============================================================*/
create table bus_oper_report
(
   id                   bigint not null auto_increment,
   report_date          varchar(10) comment '报表日期',
   trade_code           varchar(4) comment '交易码',
   trade_num            int comment '交易笔数',
   trade_sum            decimal(15,2) comment '交易金额',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_order                                             */
/*==============================================================*/
create table bus_order
(
   id                   bigint not null auto_increment comment '主键',
   order_code           varchar(32) comment '订单号',
   money_sum            decimal(9,2) comment '订单总金额',
   buy_user             bigint comment '购买人',
   order_status         char(1) comment '订单状态',
   status               char(1) comment '状态',
   oper_user            bigint comment '操作人',
   finish_time          datetime comment '完成时间',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_order_detail                                      */
/*==============================================================*/
create table bus_order_detail
(
   id                   bigint not null auto_increment comment '主键',
   goods_id             char(10) comment '商品id',
   order_id             bigint comment '订单id',
   num                  int comment '数量',
   sum                  decimal(9,2) comment '金额',
   detail_status        char(1) comment '详情状态',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_trade_record                                      */
/*==============================================================*/
create table bus_trade_record
(
   id                   bigint not null auto_increment comment '主键',
   idserial             varchar(32) comment '流水号',
   trade_code           varchar(4) comment '交易代码',
   relation_id          varchar(32) comment '关联id（订单号，或流水号）',
   user_id              bigint comment '交易用户',
   amount               decimal(15,2) comment '交易金额',
   from_acc             varchar(10) comment '来源账户',
   to_acc               varchar(10) comment '目标账户',
   remark               varchar(50) comment '备注',
   status               char(1) comment '状态',
   create_time          datetime comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: bus_user                                              */
/*==============================================================*/
create table bus_user
(
   id                   bigint not null auto_increment comment '主键',
   number               varchar(20) comment '编号',
   name                 varchar(30) comment '姓名',
   sex                  char(1) comment '性别',
   age                  int comment '年龄',
   nation               bigint comment '民族',
   id_number            varchar(18) comment '身份证号',
   photo                varchar(30) comment '照片',
   mobile               varchar(11) comment '手机号',
   area                 varchar(20) comment '监区',
   build                varchar(20) comment '楼号',
   room                 varchar(20) comment '房间',
   bed                  varchar(20) comment '床号',
   dept                 bigint comment '部门',
   pcode                bigint comment '身份',
   password             varchar(32) comment '密码（用于登录）',
   salt                 varchar(20) comment '盐加密',
   deposit              decimal(9,2) comment '押金',
   balance              decimal(15,2) comment '账户余额',
   already_cost         decimal(9,2) comment '当月已经消费',
   acc_status           char(1) comment '账户状态',
   status               char(1) comment '状态',
   remark               varchar(50) comment '备注',
   oper_user            bigint comment '操作人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_closed_person
-- ----------------------------
DROP TABLE IF EXISTS `bus_closed_person`;
CREATE TABLE `bus_closed_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='销户人员管理';

SET FOREIGN_KEY_CHECKS = 1;

alter table bus_user comment '业务信息表（犯人信息）';

