-- ----------------------------
-- Table structure for icc_param
-- ----------------------------
DROP TABLE IF EXISTS `icc_param`;
CREATE TABLE `icc_param` (
  `id`                 bigint            NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id`          bigint            NOT NULL                COMMENT '租户ID',
  `module_id`          bigint            NOT NULL                COMMENT '模块ID',
  `user_id`            bigint                     DEFAULT NULL   COMMENT '用户ID',
  `key`                varchar(32)       NOT NULL                COMMENT '配置KEY',
  `value`              varchar(64)       NOT NULL                COMMENT '配置键值',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_param_id` (`tenant_id`, `module_id`),
  KEY `idx_param_user_id` (`user_id`),
  KEY `idx_param_key` (`key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='配置表';

-- ----------------------------
-- Table structure for icc_template
-- ----------------------------
DROP TABLE IF EXISTS `icc_template`;
CREATE TABLE `icc_template` (
  `id`                 bigint            NOT NULL                COMMENT '主键-0',
  `tenant_id`          bigint            NOT NULL                COMMENT '租户ID',
  `code`               varchar(16)       NOT NULL                COMMENT '编码',
  `name`               varchar(64)       NOT NULL                COMMENT '名称',
  `content`            text              NOT NULL                COMMENT '模板内容',
  `remark`             varchar(512)               DEFAULT NULL   COMMENT '描述',
  `is_invalid`         bit               NOT NULL DEFAULT b'0'   COMMENT '是否失效:0.有效;1.失效',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_template_tenant_id` (`tenant_id`),
  KEY `idx_template_code` (`code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='报表模板表';

-- ----------------------------
-- Table structure for icc_report
-- ----------------------------
DROP TABLE IF EXISTS `icc_report`;
CREATE TABLE `icc_report` (
  `id`                 bigint            NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id`          bigint            NOT NULL                COMMENT '租户ID',
  `content`            blob              NOT NULL                COMMENT '模板内容',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_report_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='报表实例表';

-- ----------------------------
-- Table structure for icd_area
-- ----------------------------
DROP TABLE IF EXISTS `icd_area`;
CREATE TABLE `icd_area` (
  `id`                 bigint            NOT NULL                COMMENT '主键-1',
  `parent_id`          bigint                     DEFAULT NULL   COMMENT '父级ID',
  `code`               varchar(16)                DEFAULT NULL   COMMENT '编码',
  `name`               varchar(64)       NOT NULL                COMMENT '中文名称',
  `alias`              varchar(32)                DEFAULT NULL   COMMENT '简称',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_area_parent_id` (`parent_id`),
  KEY `idx_area_code` (`code`),
  KEY `idx_area_name` (`name`),
  KEY `idx_area_alias` (`alias`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='行政区划表';


-- ----------------------------
-- Table structure for icd_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `icd_dict_key`;
CREATE TABLE `icd_dict_key` (
  `id`                 bigint            NOT NULL                COMMENT '主键-2',
  `app_id`             bigint            NOT NULL                COMMENT '应用ID',
  `app_name`           varchar(64)       NOT NULL                COMMENT '应用名称',
  `code`               varchar(16)       NOT NULL                COMMENT '编码',
  `name`               varchar(64)       NOT NULL                COMMENT '名称',
  `remark`             varchar(512)               DEFAULT NULL   COMMENT '描述',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_app_id` (`app_id`),
  KEY `idx_dict_code` (`code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='字典表';

-- ----------------------------
-- Table structure for icd_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `icd_dict_value`;
CREATE TABLE `icd_dict_value` (
  `id`                 bigint            NOT NULL                COMMENT '主键-3',
  `dict_id`            bigint            NOT NULL                COMMENT '字典ID',
  `tenant_id`          bigint                     DEFAULT NULL   COMMENT '租户ID',
  `index`              int unsigned      NOT NULL                COMMENT '序号',
  `code`               varchar(16)                DEFAULT NULL   COMMENT '编码',
  `value`              varchar(64)       NOT NULL                COMMENT '键值',
  `extend`             json                       DEFAULT NULL   COMMENT '详情',
  `remark`             varchar(512)               DEFAULT NULL   COMMENT '描述',
  `creator`            varchar(64)       NOT NULL                COMMENT '创建人',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_dict_id` (`dict_id`),
  KEY `idx_dict_tenant_id` (`tenant_id`),
  KEY `idx_dict_code` (`code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='字典键值表';


-- ----------------------------
-- Table structure for ici_interface
-- ----------------------------
DROP TABLE IF EXISTS `ici_interface`;
CREATE TABLE `ici_interface` (
  `id`                 bigint            NOT NULL                COMMENT '主键-4',
  `name`               varchar(64)       NOT NULL                COMMENT '接口名称',
  `method`             varchar(8)        NOT NULL                COMMENT 'HTTP请求方法',
  `url`                varchar(128)      NOT NULL                COMMENT '接口URL',
  `auth_code`          varchar(32)                DEFAULT NULL   COMMENT '授权码,如接口需要鉴权,则必须设置授权码',
  `limit_gap`          int(10) unsigned           DEFAULT 0      COMMENT '最小间隔(秒),0表示无调用时间间隔',
  `limit_cycle`        int(10) unsigned           DEFAULT NULL   COMMENT '限流周期(秒),NULL表示不进行周期性限流',
  `limit_max`          int(10) unsigned           DEFAULT NULL   COMMENT '限制次数/限流周期,NULL表示不进行周期性限流',
  `message`            varchar(32)                DEFAULT NULL   COMMENT '限流消息',
  `remark`             varchar(512)               DEFAULT NULL   COMMENT '描述',
  `need_token`         bit               NOT NULL DEFAULT b'0'   COMMENT '是否需要一次性Token:0.不需要;1.需要',
  `is_verify`          bit               NOT NULL DEFAULT b'0'   COMMENT '是否验证Token:0.公开接口,不需要验证Token;1.私有接口,需要验证Token',
  `is_limit`           bit               NOT NULL DEFAULT b'0'   COMMENT '是否限流:0.不限流;1.限流',
  `is_log_result`      bit               NOT NULL DEFAULT b'0'   COMMENT '是否日志输出返回值',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_interface_hash` (`method`, `url`) USING BTREE,
  KEY `idx_interface_created_time` (`created_time`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='接口配置表';


-- ----------------------------
-- Table structure for icl_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `icl_operate_log`;
CREATE TABLE `icl_operate_log` (
  `id`                 bigint            NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id`             bigint            NOT NULL                COMMENT '应用ID',
  `business`           varchar(16)       NOT NULL                COMMENT '业务名称',
  `tenant_id`          bigint                     DEFAULT NULL   COMMENT '租户ID',
  `business_id`        bigint                     DEFAULT NULL   COMMENT '业务ID',
  `type`               varchar(16)       NOT NULL                COMMENT '类型',
  `content`            json                       DEFAULT NULL   COMMENT '日志内容',
  `creator`            varchar(32)       NOT NULL                COMMENT '创建人,系统自动为系统',
  `creator_id`         bigint            NOT NULL                COMMENT '创建人ID,系统自动为32个0',
  `created_time`       datetime          NOT NULL                COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_operate_log_app_id&business` (`app_id`, `business`) USING BTREE,
  KEY `idx_operate_log_tenant_id` (`tenant_id`) USING BTREE,
  KEY `idx_operate_log_business_id` (`business_id`) USING BTREE,
  KEY `idx_operate_log_created_time` (`created_time`) USING BTREE
) ENGINE = MyISAM DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='操作日志记录表';


-- ----------------------------
-- 初始化接口配置
-- ---------------------------- 
INSERT `ici_interface`(`id`, `name`, `method`, `url`, `auth_code`, `limit_gap`, `limit_cycle`, `limit_max`, `message`, `is_verify`, `is_limit`, `created_time`) VALUES 
(134659190399434773, '获取Code', 'GET', '/base/auth/v1.0/tokens/codes', NULL, 1, 86400, 360, '获取Code接口每24小时调用次数为360次,请合理使用', 0, 1, now()),
(134660755520749589, '获取Token', 'POST', '/base/auth/v1.0/tokens', NULL, 1, 86400, 360, '获取Token接口每24小时调用次数为360次,请合理使用', 0, 1, now()),
(134661909612855317, '通过微信授权码获取Token', 'POST', '/base/auth/v1.0/tokens/withWechatCode', NULL, 1, 86400, 360, '获取Token接口每24小时调用次数为360次,请合理使用', 0, 1, now()),
(134667548749725717, '通过微信UnionId获取Token', 'POST', '/base/auth/v1.0/tokens/withWechatUnionId', NULL, 1, 86400, 360, '获取Token接口每24小时调用次数为360次,请合理使用', 0, 1, now()),
(134668047163064341, '验证Token', 'GET', '/base/auth/v1.0/tokens/status', NULL, NULL, NULL, NULL, NULL, 1, 0, now()),
(134668810484449301, '刷新Token', 'PUT', '/base/auth/v1.0/tokens', NULL, 10, 3600, 10, '刷新Token接口每小时调用次数为10次,请合理使用', 0, 1, now()),
(134671174327074837, '用户账号离线', 'DELETE', '/base/auth/v1.0/tokens', NULL, 10, NULL, NULL, NULL, 1, 1, now()),
(134676639631015957, '获取提交数据用临时Token', 'GET', '/base/auth/v1.0/tokens', NULL, NULL, 300, 30, '获取临时Token接口每5分钟调用次数为30次,请合理使用', 1, 1, now()),
(134685654863839253, '获取用户可选租户', 'GET', '/base/auth/v1.0/{id}/tenants', NULL, 1, NULL, NULL, NULL, 0, 1, now()),
(134686969383878677, '获取用户导航栏', 'GET', '/base/auth/v1.0/navigators', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(134687950754545685, '获取模块功能', 'GET', '/base/auth/v1.0/navigators/{id}/functions', NULL, 1, NULL, NULL, NULL, 1, 1, now()),

-- 角色接口配置
(134689591851483157, '获取角色列表', 'GET', '/base/role/v1.0/roles', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134690056429371413, '获取角色详情', 'GET', '/base/role/v1.0/roles/{id}', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134690988932202517, '获取角色成员列表', 'GET', '/base/role/v1.0/roles/{id}/members', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134691582883397653, '获取角色成员用户列表', 'GET', '/base/role/v1.0/roles/{id}/users', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134983834578976789, '获取角色功能权限列表', 'GET', '/base/role/v1.0/roles/{id}/funcs', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134984513884258325, '获取角色数据权限列表', 'GET', '/base/role/v1.0/roles/{id}/datas', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134984923705507861, '获取角色可用成员用户列表', 'GET', '/base/role/v1.0/roles/{id}/users/other', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134985740739149845, '获取角色可用成员用户组列表', 'GET', '/base/role/v1.0/roles/{id}/groups/other', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134992083424378901, '获取角色可用成员职位列表', 'GET', '/base/role/v1.0/roles/{id}/orgs/other', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134992444394569749, '获取角色可用应用列表', 'GET', '/base/role/v1.0/apps', 'getRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134992717330513941, '新增角色', 'POST', '/base/role/v1.0/roles', 'newRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134992978522406933, '编辑角色', 'PUT', '/base/role/v1.0/roles/{id}', 'editRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134993521420533781, '删除角色', 'DELETE', '/base/role/v1.0/roles/{id}', 'deleteRole', 1, NULL, NULL, NULL, 1, 1, now()),
(134993819526496277, '添加角色成员', 'POST', '/base/role/v1.0/roles/{id}/members', 'addRoleMember', 1, NULL, NULL, NULL, 1, 1, now()),
(134994449582260245, '移除角色成员', 'DELETE', '/base/role/v1.0/roles/{id}/members', 'removeRoleMember', 1, NULL, NULL, NULL, 1, 1, now()),
(134996593777573909, '设置角色功能权限', 'PUT', '/base/role/v1.0/roles/{id}/funcs', 'setRoleFunc', 0, NULL, NULL, NULL, 1, 0, now()),
(135025601193967637, '设置角色数据权限', 'PUT', '/base/role/v1.0/roles/{id}/datas', 'setRoleData', 0, NULL, NULL, NULL, 1, 0, now()),
(135037646245199893, '获取角色日志列表', 'GET', '/base/role/v1.0/roles/logs', 'getRoleLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135039825387454485, '获取角色日志详情', 'GET', '/base/role/v1.0/roles/logs/{id}', 'getRoleLog', 1, NULL, NULL, NULL, 1, 1, now()),

-- 组织机构接口配置
(135040284953149461, '获取组织机构列表', 'GET', '/base/organize/v1.0/organizes', 'getOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135042969441927189, '获取组织机构详情', 'GET', '/base/organize/v1.0/organizes/{id}', 'getOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135046767367421973, '新增组织机构', 'POST', '/base/organize/v1.0/organizes', 'newOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135051787378884629, '编辑组织机构', 'PUT', '/base/organize/v1.0/organizes/{id}', 'editOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135053741224099861, '删除组织机构', 'DELETE', '/base/organize/v1.0/organizes/{id}', 'deleteOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135054720719912981, '获取组织机构用户列表', 'GET', '/base/organize/v1.0/organizes/{id}/users', 'getOrganize', 1, NULL, NULL, NULL, 1, 1, now()),
(135055094210101269, '添加组织机构成员', 'POST', '/base/organize/v1.0/organizes/{id}/members', 'addOrganizeMember', 1, NULL, NULL, NULL, 1, 1, now()),
(135061639618625557, '移除组织机构成员', 'DELETE', '/base/organize/v1.0/organizes/{id}/members', 'removeOrganizeMember', 1, NULL, NULL, NULL, 1, 1, now()),
(135065610156507157, '获取组织机构日志列表', 'GET', '/base/organize/v1.0/organizes/logs', 'getOrganizeLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135066007965270037, '获取组织机构日志详情', 'GET', '/base/organize/v1.0/organizes/logs/{id}', 'getOrganizeLog', 1, NULL, NULL, NULL, 1, 1, now()),

-- 租户接口配置
(135066350715404309, '获取租户列表', 'GET', '/base/tenant/v1.0/tenants', 'getTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135069907086737429, '获取租户详情', 'GET', '/base/tenant/v1.0/tenants/{id}', 'getTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135070495031689237, '获取租户绑定应用列表', 'GET', '/base/tenant/v1.0/tenants/{id}/apps', 'getTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135071863511449621, '获取租户绑定用户列表', 'GET', '/base/tenant/v1.0/tenants/{id}/users', 'getTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135072806177079317, '新增租户', 'POST', '/base/tenant/v1.0/tenants', 'newTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135072926994006037, '编辑租户', 'PUT', '/base/tenant/v1.0/tenants/{id}', 'editTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135073344310476821, '删除租户', 'DELETE', '/base/tenant/v1.0/tenants/{id}', 'deleteTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135075359241535509, '审核租户', 'PUT', '/base/tenant/v1.0/tenants/{id}/status', 'auditTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135075623210057749, '禁用租户', 'PUT', '/base/tenant/v1.0/tenants/{id}/disable', 'disableTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135076131253518357, '启用租户', 'PUT', '/base/tenant/v1.0/tenants/{id}/enable', 'enableTenant', 1, NULL, NULL, NULL, 1, 1, now()),
(135078060545277973, '获取租户未绑定应用列表', 'GET', '/base/tenant/v1.0/tenants/{id}/unbounds', 'bindApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135078710482042901, '租户绑定应用', 'POST', '/base/tenant/v1.0/tenants/{id}/apps', 'bindApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135084682432741397, '租户解绑应用', 'DELETE', '/base/tenant/v1.0/tenants/{id}/apps', 'unbindApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135085390183792661, '租户续租应用', 'PUT', '/base/tenant/v1.0/tenants/{id}/apps', 'rentTenantApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135085903797288981, '获取租户日志列表', 'GET', '/base/tenant/v1.0/tenants/logs', 'getTenantLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135086200347164693, '获取租户日志详情', 'GET', '/base/tenant/v1.0/tenants/logs/{id}', 'getTenantLog', 1, NULL, NULL, NULL, 1, 1, now()),

-- 用户组接口配置
(135086579709378581, '获取用户组列表', 'GET', '/base/user/v1.0/groups', 'getGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135086938536280085, '获取用户组详情', 'GET', '/base/user/v1.0/groups/{id}', 'getGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135087757952286741, '获取用户组成员列表', 'GET', '/base/user/v1.0/groups/{id}/members', 'getGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135088549279039509, '新增用户组', 'POST', '/base/user/v1.0/groups', 'newGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135089392644522005, '编辑用户组', 'PUT', '/base/user/v1.0/groups/{id}', 'editGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135089899907842069, '删除用户组', 'DELETE', '/base/user/v1.0/groups/{id}', 'deleteGroup', 1, NULL, NULL, NULL, 1, 1, now()),
(135090175217762325, '获取用户组可用成员列表', 'GET', '/base/user/v1.0/groups/{id}/others', 'addGroupMember', 1, NULL, NULL, NULL, 1, 1, now()),
(135090513446436885, '添加用户组成员', 'POST', '/base/user/v1.0/groups/{id}/members', 'addGroupMember', 1, NULL, NULL, NULL, 1, 1, now()),
(135090858125950997, '移除用户组成员', 'DELETE', '/base/user/v1.0/groups/{id}/members', 'removeGroupMember', 1, NULL, NULL, NULL, 1, 1, now()),
(135091150846427157, '获取用户组日志列表', 'GET', '/base/user/v1.0/groups/logs', 'getGroupLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135091584336134165, '获取用户组日志详情', 'GET', '/base/user/v1.0/groups/logs/{id}', 'getGroupLog', 1, NULL, NULL, NULL, 1, 1, now()),

-- 应用接口配置
(135092211485245461, '获取应用列表', 'GET', '/base/resource/v1.0/apps', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135095533424345109, '获取应用详情', 'GET', '/base/resource/v1.0/apps/{id}', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135096027370749973, '新增应用', 'POST', '/base/resource/v1.0/apps', 'newApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135096497157963797, '编辑应用', 'PUT', '/base/resource/v1.0/apps/{id}', 'editApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135097652764213269, '删除应用', 'DELETE', '/base/resource/v1.0/apps/{id}', 'deleteApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135098572088541205, '获取导航列表', 'GET', '/base/resource/v1.0/apps/{id}/navigators', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135100084332265493, '获取导航详情', 'GET', '/base/resource/v1.0/navigators/{id}', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135101297375313941, '新增导航', 'POST', '/base/resource/v1.0/navigators', 'newNav', 1, NULL, NULL, NULL, 1, 1, now()),
(135101682777325589, '编辑导航', 'PUT', '/base/resource/v1.0/navigators/{id}', 'editNav', 1, NULL, NULL, NULL, 1, 1, now()),
(135101990886703125, '删除导航', 'DELETE', '/base/resource/v1.0/navigators/{id}', 'deleteNav', 1, NULL, NULL, NULL, 1, 1, now()),
(135102323142688789, '获取功能列表', 'GET', '/base/resource/v1.0/navigators/{id}/functions', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135102633714122773, '获取功能详情', 'GET', '/base/resource/v1.0/functions/{id}', 'getApp', 1, NULL, NULL, NULL, 1, 1, now()),
(135103286515597333, '新增功能', 'POST', '/base/resource/v1.0/functions', 'newFunc', 1, NULL, NULL, NULL, 1, 1, now()),
(135103719283884053, '编辑功能', 'PUT', '/base/resource/v1.0/functions/{id}', 'editFunc', 1, NULL, NULL, NULL, 1, 1, now()),
(135104834616426517, '删除功能', 'DELETE', '/base/resource/v1.0/functions/{id}', 'deleteFunc', 1, NULL, NULL, NULL, 1, 1, now()),
(135106155385978901, '获取应用日志列表', 'GET', '/base/resource/v1.0/apps/logs', 'getAppLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135106723206660117, '获取应用日志详情', 'GET', '/base/resource/v1.0/apps/logs/{id}', 'getAppLog', 1, NULL, NULL, NULL, 1, 1, now()),

-- 用户接口配置
(135106954421862421, '获取用户列表', 'GET', '/base/user/manage/v1.0/users', 'getUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135107787855233045, '获取用户详情', 'GET', '/base/user/manage/v1.0/users/{id}', 'getUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135108285194829845, '获取用户功能授权', 'GET', '/base/user/manage/v1.0/users/{id}/functions', 'getUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135108617727639573, '新增用户', 'POST', '/base/user/manage/v1.0/users', 'newUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135108849588764693, '编辑用户', 'PUT', '/base/user/manage/v1.0/users/{id}', 'editUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135109135875178517, '删除用户', 'DELETE', '/base/user/manage/v1.0/users/{id}', 'deleteUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135110869842395157, '禁用用户', 'PUT', '/base/user/manage/v1.0/users/{id}/disable', 'bannedUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135111115100127253, '启用用户', 'PUT', '/base/user/manage/v1.0/users/{id}/enable', 'releaseUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135111142916751381, '重置用户密码', 'PUT', '/base/user/manage/v1.0/users/{id}/password', 'resetPassword', 1, NULL, NULL, NULL, 1, 1, now()),
(135111305437642773, '获取可邀请用户列表', 'GET', '/base/user/manage/v1.0/users/others', 'inviteUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135112186505723925, '邀请用户', 'POST', '/base/user/manage/v1.0/users/{id}/relation', 'inviteUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135112827974189077, '清退用户', 'DELETE', '/base/user/manage/v1.0/users/{id}/relation', 'removeUser', 1, NULL, NULL, NULL, 1, 1, now()),
(135113144581226517, '获取用户日志列表', 'GET', '/base/user/manage/v1.0/users/logs', 'getUserLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135115482935066645, '获取用户日志详情', 'GET', '/base/user/manage/v1.0/users/logs/{id}', 'getUserLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135115791191244821, '获取用户详情', 'GET', '/base/user/v1.0/users/myself', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135116279429201941, '注册用户', 'POST', '/base/user/v1.0/users', NULL, 1, NULL, NULL, NULL, 0, 1, now()),
(135116625534779413, '更新用户昵称', 'PUT', '/base/user/v1.0/users/name', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135116740743921685, '更新用户手机号', 'PUT', '/base/user/v1.0/users/mobile', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135117051004977173, '更新用户Email', 'PUT', '/base/user/v1.0/users/email', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135118329873432597, '更新用户头像', 'PUT', '/base/user/v1.0/users/head', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135119846986743829, '更新用户备注', 'PUT', '/base/user/v1.0/users/remark', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135120694030630933, '修改密码', 'PUT', '/base/user/v1.0/users/password', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135121284928372757, '重置密码', 'POST', '/base/user/v1.0/users/password', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135122059712790549, '设置支付密码', 'POST', '/base/user/v1.0/users/password/pay', NULL, 1, NULL, NULL, NULL, 1, 1, now()),

-- 信息中心接口配置
(135122720386973717, '获取计划任务列表', 'GET', '/common/message/v1.0/schedules', 'getSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135123058024251413, '获取计划任务详情', 'GET', '/common/message/v1.0/schedules/{id}', 'getSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135123534128087061, '立即执行计划任务', 'PUT', '/common/message/v1.0/schedules/{id}', 'executeSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135124181124644885, '删除计划任务', 'DELETE', '/common/message/v1.0/schedules/{id}', 'deleteSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135125020966912021, '禁用计划任务', 'PUT', '/common/message/v1.0/schedules/{id}/disable', 'disableSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135125379282108437, '启用计划任务', 'PUT', '/common/message/v1.0/schedules/{id}/enable', 'enableSchedule', 1, NULL, NULL, NULL, 1, 1, now()),
(135125576481505301, '获取计划任务日志列表', 'GET', '/common/message/v1.0/schedules/logs', 'getScheduleLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135125605157961749, '获取计划任务日志详情', 'GET', '/common/message/v1.0/schedules/logs/{id}', 'getScheduleLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135125966853767189, '获取消息场景列表', 'GET', '/common/message/v1.0/scenes', 'getScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135126317807960085, '获取消息场景详情', 'GET', '/common/message/v1.0/scenes/{id}', 'getScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135128923200552981, '新增消息场景', 'POST', '/common/message/v1.0/scenes', 'newScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135133177831227413, '编辑消息场景', 'PUT', '/common/message/v1.0/scenes/{id}', 'editScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135133751360356373, '删除消息场景', 'DELETE', '/common/message/v1.0/scenes/{id}', 'deleteScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135135163532181525, '获取场景配置列表', 'GET', '/common/message/v1.0/scenes/{id}/configs', 'getScene', 1, NULL, NULL, NULL, 1, 1, now()),
(135137013232828437, '新增场景配置', 'POST', '/common/message/v1.0/scenes/{id}/configs', 'newSceneConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135137370327482389, '编辑场景配置', 'PUT', '/common/message/v1.0/scenes/configs/{id}', 'editSceneConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135138035577651221, '删除场景配置', 'DELETE', '/common/message/v1.0/scenes/configs/{id}', 'deleteSceneConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135138862723760149, '获取消息场景日志列表', 'GET', '/common/message/v1.0/scenes/logs', 'getSceneLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135144215079288853, '获取消息场景日志详情', 'GET', '/common/message/v1.0/scenes/logs/{id}', 'getSceneLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135146176637829141, '发送短信验证码', 'POST', '/common/message/v1.0/codes', NULL, 10, 86400, 30, '今日验证码次数已达上限,请合理使用短信验证码', 0, 1, now()),
(135150353984258069, '验证短信验证码', 'GET', '/common/message/v1.0/codes/{key}/status', NULL, NULL, NULL, NULL, NULL, 0, 0, now()),
(135150411962122261, '发送送标准消息', 'POST', '/common/message/v1.0/messages', 'sendMessage', 10, NULL, NULL, NULL, 1, 1, now()),
(135151007914000405, '发送自定义消息', 'POST', '/common/message/v1.0/customs', 'sendCustomMessage', 10, NULL, NULL, NULL, 1, 1, now()),
(135151232896466965, '获取用户消息列表', 'GET', '/common/message/v1.0/messages', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135151644043116565, '获取用户消息详情', 'GET', '/common/message/v1.0/messages/{id}', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135151797638529045, '删除用户消息', 'DELETE', '/common/message/v1.0/messages', NULL, 1, NULL, NULL, NULL, 1, 1, now()),

-- 上传文件接口
(135152070226346005, '获取七牛上传Token', 'GET', '/common/file/v1.0/token', 'getQiniuToken', 1, NULL, NULL, NULL, 1, 1, now()),
(135152151725867029, '文件直接上传', 'POST', '/common/file/v1.0/upload', 'uploadFile', 1, NULL, NULL, NULL, 1, 1, now()),
(135152593671290901, '文件流上传', 'POST', '/common/file/v1.0/stream', 'uploadFile', 1, NULL, NULL, NULL, 1, 1, now()),
(135153035142758421, 'Base64编码上传', 'POST', '/common/file/v1.0/data', 'uploadFile', 1, NULL, NULL, NULL, 1, 1, now()),

-- 基础数据接口配置
(135153895063158805, '获取接口配置列表', 'GET', '/common/config/v1.0/configs', 'getConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135154035459096597, '获取接口配置详情', 'GET', '/common/config/v1.0/configs/{id}', 'getConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135154244377378837, '新增接口配置', 'POST', '/common/config/v1.0/configs', 'newConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135154323863633941, '编辑接口配置', 'PUT', '/common/config/v1.0/configs/{id}', 'editConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135154447327166485, '删除接口配置', 'DELETE', '/common/config/v1.0/configs/{id}', 'deleteConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135154908910321685, '加载接口配置表', 'GET', '/common/config/v1.0/configs/load', 'loadConfig', 1, NULL, NULL, NULL, 1, 1, now()),
(135155338386079765, '获取接口配置日志列表', 'GET', '/common/config/v1.0/configs/logs', 'getConfigLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135155347529662485, '获取接口配置日志详情', 'GET', '/common/config/v1.0/configs/logs/{id}', 'getConfigLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135156016869277717, '获取选项参数列表', 'GET', '/common/param/v1.0/params', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135156506814316565, '获取选项参数', 'GET', '/common/param/v1.0/params/value', NULL, NULL, NULL, NULL, NULL, 1, 0, now()),
(135156751891693589, '更新选项参数', 'PUT', '/common/param/v1.0/params', 'setParam', 1, NULL, NULL, NULL, 1, 1, now()),

(135156939632934933, '获取报表模板列表', 'GET', '/common/report/v1.0/templates', 'getTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135157717189787669, '获取报表模板详情', 'GET', '/common/report/v1.0/templates/{id}', 'getTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135158329553977365, '获取报表模板内容', 'GET', '/common/report/v1.0/templates/{id}/content', NULL, NULL, NULL, NULL, NULL, 1, 0, now()),
(135158403285647381, '导入报表模板', 'POST', '/common/report/v1.0/templates', 'importTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135158807293591573, '复制报表模板', 'POST', '/common/report/v1.0/templates/{id}', 'copyTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135159422207918101, '编辑报表模板', 'PUT', '/common/report/v1.0/templates/{id}', 'editTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135159515514404885, '删除报表模板', 'DELETE', '/common/report/v1.0/templates/{id}', 'deleteTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135159970030157845, '设计报表模板', 'PUT', '/common/report/v1.0/templates/{id}/content', 'designTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135160377808781333, '禁用报表模板', 'PUT', '/common/report/v1.0/templates/{id}/disable', 'disableTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135160400734846997, '启用报表模板', 'PUT', '/common/report/v1.0/templates/{id}/enable', 'enableTemplate', 1, NULL, NULL, NULL, 1, 1, now()),
(135160866914959381, '获取报表实例详情', 'GET', '/common/report/v1.0/reports/{id}', NULL, NULL, NULL, NULL, NULL, 1, 0, now()),
(135166290804342805, '新增报表实例', 'POST', '/common/report/v1.0/reports', 'newReport', 1, NULL, NULL, NULL, 1, 1, now()),
(135169520712024085, '获取报表模板日志列表', 'GET', '/common/report/v1.0/templates/logs', 'getTemplateLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135171344223436821, '获取报表模板日志详情', 'GET', '/common/report/v1.0/templates/logs/{id}', 'getTemplateLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135171600923230229, '查询全部省级行政区划', 'GET', '/common/area/v1.0/areas/provinces', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135173284881760277, '查询指定行政区划的下级区划', 'GET', '/common/area/v1.0/areas/{is}/subs', NULL, NULL, NULL, NULL, NULL, 1, 0, now()),
(135173440305889301, '获取全部行政区划', 'GET', '/common/area/v1.0/areas', "getArea", 1, NULL, NULL, NULL, 1, 1, now()),
(135175504108978197, '新增行政区划', 'POST', '/common/area/v1.0/areas', "newArea", 1, NULL, NULL, NULL, 1, 1, now()),
(135175590381617173, '编辑行政区划', 'PUT', '/common/area/v1.0/areas/{id}', "editArea", 1, NULL, NULL, NULL, 1, 1, now()),
(135176858319716373, '删除行政区划', 'DELETE', '/common/area/v1.0/areas/{id}', "deleteArea", 1, NULL, NULL, NULL, 1, 1, now()),
(135177297907941397, '获取行政区划日志列表', 'GET', '/common/area/v1.0/areas/logs', 'getAreaLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135177720211439637, '获取行政区划日志详情', 'GET', '/common/area/v1.0/areas/logs/{id}', 'getAreaLog', 1, NULL, NULL, NULL, 1, 1, now()),

(135177814776217621, '获取字典列表', 'GET', '/common/dict/v1.0/dicts', "getDict", 1, NULL, NULL, NULL, 1, 1, now()),
(135178120691974165, '获取字典键值集合', 'GET', '/common/dict/v1.0/dicts/{id}', "getDict", 1, NULL, NULL, NULL, 1, 1, now()),
(135178348383961109, '获取指定键名的键值集合', 'GET', '/common/dict/v1.0/dicts/values', NULL, 1, NULL, NULL, NULL, 1, 1, now()),
(135178523206746133, '新增字典', 'POST', '/common/dict/v1.0/dicts', "newDict", 1, NULL, NULL, NULL, 1, 1, now()),
(135178642207539221, '编辑字典', 'PUT', '/common/dict/v1.0/dicts/{id}', "editDict", 1, NULL, NULL, NULL, 1, 1, now()),
(135178938505756693, '删除字典', 'DELETE', '/common/dict/v1.0/dicts/{id}', "deleteDict", 1, NULL, NULL, NULL, 1, 1, now()),
(135179306799202325, '新增字典键值', 'POST', '/common/dict/v1.0/dicts/{id}/keys', "newValue", 1, NULL, NULL, NULL, 1, 1, now()),
(135179539595657237, '编辑字典键值', 'PUT', '/common/dict/v1.0/dicts/keys/{id}', "editValue", 1, NULL, NULL, NULL, 1, 1, now()),
(135179577952567317, '删除字典键值', 'DELETE', '/common/dict/v1.0/dicts/keys/{id}', "deleteValue", 1, NULL, NULL, NULL, 1, 1, now()),
(135179920992108565, '获取字典日志列表', 'GET', '/common/dict/v1.0/dicts/logs', 'getDictLog', 1, NULL, NULL, NULL, 1, 1, now()),
(135180372420853781, '获取字典日志详情', 'GET', '/common/dict/v1.0/dicts/logs/{id}', 'getDictLog', 1, NULL, NULL, NULL, 1, 1, now());
