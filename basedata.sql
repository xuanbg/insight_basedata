
-- ----------------------------
-- Table structure for icd_area
-- ----------------------------
DROP TABLE IF EXISTS `icd_area`;
CREATE TABLE `icd_area` (
  `id` char(32) NOT NULL COMMENT 'UUID主键',
  `parent_id` char(32) DEFAULT NULL COMMENT '父级ID',
  `code` varchar(16) DEFAULT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL COMMENT '中文名称',
  `alias` varchar(32) DEFAULT NULL COMMENT '简称',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `creator_id` char(32) NOT NULL COMMENT '创建人ID',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_area_parent_id` (`parent_id`),
  KEY `idx_area_code` (`code`),
  KEY `idx_area_name` (`name`),
  KEY `idx_area_alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='行政区划表';


-- ----------------------------
-- Table structure for icd_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `icd_dict_key`;
CREATE TABLE `icd_dict_key` (
  `id` char(32) NOT NULL COMMENT 'UUID主键',
  `parent_id` char(32) DEFAULT NULL COMMENT '父级ID',
  `code` varchar(16) DEFAULT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL COMMENT '中文名称',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `creator_id` char(32) NOT NULL COMMENT '创建人ID',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_dict_parent_id` (`parent_id`),
  KEY `idx_dict_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='字典表';

-- ----------------------------
-- Table structure for icd_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `icd_dict_value`;
CREATE TABLE `icd_dict_value` (
  `id` char(32) NOT NULL COMMENT 'UUID主键',
  `key_id` char(32) DEFAULT NULL COMMENT '字典ID',
  `index` int(11) unsigned NOT NULL COMMENT '序号',
  `code` varchar(16) DEFAULT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL COMMENT '中文名称',
  `detail` json DEFAULT NULL COMMENT '详情',
  `is_invalid` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否失效：0、正常；1、失效',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `creator_id` char(32) NOT NULL COMMENT '创建人ID',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_dict_key_id` (`key_id`),
  KEY `idx_dict_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='字典内容表';