INSERT INTO `sys_menu` VALUES (4, '业务管理', 0, 0, '', 'M', '0', '', 'fa fa-bars', 'admin', '2018-08-05 21:46:42', 'admin', '2018-08-05 21:50:10', '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备管理', '1', '1', '/system/device', 'C', '0', 'system:device:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '设备菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备查询', @parentId, '1',  '#',  'F', '0', 'system:device:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备新增', @parentId, '2',  '#',  'F', '0', 'system:device:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备修改', @parentId, '3',  '#',  'F', '0', 'system:device:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备删除', @parentId, '4',  '#',  'F', '0', 'system:device:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商户管理', '1', '1', '/system/merchant', 'C', '0', 'system:merchant:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '商户菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商户查询', @parentId, '1',  '#',  'F', '0', 'system:merchant:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商户新增', @parentId, '2',  '#',  'F', '0', 'system:merchant:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商户修改', @parentId, '3',  '#',  'F', '0', 'system:merchant:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商户删除', @parentId, '4',  '#',  'F', '0', 'system:merchant:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('身份管理', '1', '1', '/system/identity', 'C', '0', 'system:identity:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '身份管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('身份管理查询', @parentId, '1',  '#',  'F', '0', 'system:identity:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('身份管理新增', @parentId, '2',  '#',  'F', '0', 'system:identity:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('身份管理修改', @parentId, '3',  '#',  'F', '0', 'system:identity:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('身份管理删除', @parentId, '4',  '#',  'F', '0', 'system:identity:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');



-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('密码修改申请', '1', '1', '/system/modifyPwd', 'C', '0', 'system:modifyPwd:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '密码修改申请菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('密码修改申请查询', @parentId, '1',  '#',  'F', '0', 'system:modifyPwd:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('密码修改审核', @parentId, '2',  '#',  'F', '0', 'system:modifyPwd:review',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');






-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类', '4', '1', '/business/goodCategory', 'C', '0', 'business:goodCategory:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '商品分类菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类查询', @parentId, '1',  '#',  'F', '0', 'business:goodCategory:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类新增', @parentId, '2',  '#',  'F', '0', 'business:goodCategory:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类修改', @parentId, '3',  '#',  'F', '0', 'business:goodCategory:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类删除', @parentId, '4',  '#',  'F', '0', 'business:goodCategory:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品管理', '4', '1', '/business/goods', 'C', '0', 'business:goods:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '商品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品查询', @parentId, '1',  '#',  'F', '0', 'business:goods:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品新增', @parentId, '2',  '#',  'F', '0', 'business:goods:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品修改', @parentId, '3',  '#',  'F', '0', 'business:goods:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品删除', @parentId, '4',  '#',  'F', '0', 'business:goods:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');




-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单详情查询', @parentId, '1',  '#',  'F', '0', 'business:orderDetail:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单详情修改', @parentId, '3',  '#',  'F', '0', 'business:orderDetail:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单管理', '4', '1', '/business/order', 'C', '0', 'business:order:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '订单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单查询', @parentId, '1',  '#',  'F', '0', 'business:order:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单完成', @parentId, '2',  '#',  'F', '0', 'business:order:finish',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('人员管理', '4', '1', '/business/person', 'C', '0', 'business:person:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '业务（犯人）菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('人员查询', @parentId, '1',  '#',  'F', '0', 'business:person:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('人员新增', @parentId, '2',  '#',  'F', '0', 'business:person:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('人员修改', @parentId, '3',  '#',  'F', '0', 'business:person:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('人员删除', @parentId, '4',  '#',  'F', '0', 'business:person:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');



-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水管理', '4', '1', '/business/tradeRecord', 'C', '0', 'business:tradeRecord:view', '#', 'admin', '2018-03-01', 'information', '2018-03-01', '流水菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水查询', @parentId, '1',  '#',  'F', '0', 'business:tradeRecord:list',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水新增', @parentId, '2',  '#',  'F', '0', 'business:tradeRecord:add',          '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水修改', @parentId, '3',  '#',  'F', '0', 'business:tradeRecord:edit',         '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水删除', @parentId, '4',  '#',  'F', '0', 'business:tradeRecord:remove',       '#', 'admin', '2018-03-01', 'information', '2018-03-01', '');






























