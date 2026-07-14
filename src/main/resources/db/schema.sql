-- 后台菜单
CREATE TABLE IF NOT EXISTS `rgh_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `parent_id` bigint DEFAULT NULL COMMENT '父权限 ID (0为顶级菜单)',
  `label` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限名称',
    `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '授权标识符',
    `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '路由地址',
    `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '路由名称',
    `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '授权路径',
    `order_num` int DEFAULT '0' COMMENT '序号',
    `type` int NOT NULL DEFAULT '0' COMMENT '类型(0 目录 1菜单，2按钮)',
    `icon` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '图标',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='后台菜单';

-- 前端导航
CREATE TABLE IF NOT EXISTS `rgh_nav` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `parent_id` bigint DEFAULT NULL COMMENT '父权限 ID (0为顶级菜单)',
  `label` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限名称',
    `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '授权标识符',
    `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '路由地址',
    `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '路由名称',
    `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '授权路径',
    `order_num` int DEFAULT '0' COMMENT '序号',
    `type` int NOT NULL DEFAULT '0' COMMENT '类型(0 目录 1菜单，2按钮)',
    `icon` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '图标',
    `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `parent_name` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '父级菜单名称',
    `created_time` datetime(6) DEFAULT NULL,
    `created_user` varchar(255) DEFAULT NULL,
    `last_modified_time` datetime(6) DEFAULT NULL,
    `last_modified_user` varchar(255) DEFAULT NULL,
    `enable` bit(1) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- 用户表
CREATE TABLE IF NOT EXISTS `rgh_user` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键Id',
  `username` VARCHAR(255) NOT NULL UNIQUE COMMENT '登录账号',
    `password` VARCHAR(255) NOT NULL COMMENT '登录密码',
    `avatar` VARCHAR(255) COMMENT '头像',
    `role` VARCHAR(255) COMMENT '角色',
    `nick_name` VARCHAR(255) COMMENT '昵称'
    ) COMMENT='用户表';

-- 角色表
CREATE TABLE IF NOT EXISTS rgh_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键Id，自增',
  name VARCHAR(255) NOT NULL COMMENT '角色名称',
  code VARCHAR(255) NOT NULL UNIQUE COMMENT  '角色编码',
  description VARCHAR(255) COMMENT '角色描述'
) COMMENT='角色表';
-- 用户角色关联表

CREATE TABLE IF NOT EXISTS rgh_user_role (
   id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键Id，自增',
   user_id BIGINT NOT NULL COMMENT '用户Id',
   role_id BIGINT NOT NULL COMMENT '角色Id'
) COMMENT='用户角色关联表';

-- 文章表
CREATE TABLE IF NOT EXISTS `rgh_article` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` VARCHAR(255) NOT NULL COMMENT '标题',
  `cover` VARCHAR(500) DEFAULT NULL COMMENT '封面图',
  `author` VARCHAR(255) DEFAULT NULL COMMENT '作者',
  `content` LONGTEXT COMMENT '正文',
  `state` BIT(1) NOT NULL DEFAULT b'1' COMMENT '状态(0草稿 1发布)',
  `top` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶',
  `download_enable` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否开启下载',
  `download_name` VARCHAR(255) DEFAULT NULL COMMENT '附件名称',
  `download_url` VARCHAR(500) DEFAULT NULL COMMENT '下载地址',
  `download_size` VARCHAR(50) DEFAULT NULL COMMENT '附件大小',
  `download_desc` VARCHAR(500) DEFAULT NULL COMMENT '附件描述',
  `view_count` INT NOT NULL DEFAULT '0' COMMENT '阅读量',
  `like_count` INT NOT NULL DEFAULT '0' COMMENT '点赞数',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_top_time` (`top`, `create_time`),
  KEY `idx_article_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 文章分类表
CREATE TABLE IF NOT EXISTS `rgh_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(255) NOT NULL COMMENT '分类名称',
  `alias` VARCHAR(255) DEFAULT NULL COMMENT '别名',
  `icon` VARCHAR(255) DEFAULT NULL COMMENT '图标',
  `enable` BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否显示在菜单',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';

-- 标签表
CREATE TABLE IF NOT EXISTS `rgh_tag` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(255) NOT NULL COMMENT '标签名',
  `alias` VARCHAR(255) DEFAULT NULL COMMENT '别名',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- 文章-分类关联表
CREATE TABLE IF NOT EXISTS `rgh_article_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` INT NOT NULL COMMENT '文章ID',
  `category_id` INT NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`),
  KEY `idx_ac_article` (`article_id`),
  KEY `idx_ac_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类关联表';

-- 文章-标签关联表
CREATE TABLE IF NOT EXISTS `rgh_article_tag` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` INT NOT NULL COMMENT '文章ID',
  `tag_id` INT NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`),
  KEY `idx_at_article` (`article_id`),
  KEY `idx_at_tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章标签关联表';

-- 评论表
CREATE TABLE IF NOT EXISTS `rgh_comment` (
  `uid` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` INT DEFAULT NULL COMMENT '父评论ID(顶级为NULL或0)',
  `username` VARCHAR(255) DEFAULT NULL COMMENT '评论人',
  `article_id` INT DEFAULT NULL COMMENT '文章ID',
  `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像',
  `link` VARCHAR(500) DEFAULT NULL COMMENT '链接',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '归属地',
  `content` TEXT COMMENT '内容',
  `level` INT DEFAULT '1' COMMENT '等级',
  `like` INT DEFAULT '0' COMMENT '点赞数',
  `state` INT NOT NULL DEFAULT '0' COMMENT '审核状态(0待审核 1通过 2拒绝)',
  `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  KEY `idx_comment_article` (`article_id`),
  KEY `idx_comment_parent` (`parent_id`),
  KEY `idx_comment_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 友情链接表
CREATE TABLE IF NOT EXISTS `rgh_link` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(255) NOT NULL COMMENT '网站名称',
  `link` VARCHAR(500) DEFAULT NULL COMMENT '网站链接',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `is_enable` BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='友情链接表';

-- 操作日志表
CREATE TABLE IF NOT EXISTS `rgh_operation_log` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `operate_type` VARCHAR(32) DEFAULT NULL COMMENT '操作类型',
  `target_id` VARCHAR(255) DEFAULT NULL COMMENT '目标ID',
  `message` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `operator_name` VARCHAR(255) DEFAULT NULL COMMENT '操作人',
  `operate_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- 首页动态卡片配置表
CREATE TABLE IF NOT EXISTS `rgh_home_card` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` INT NOT NULL COMMENT '关联分类ID',
  `title` VARCHAR(255) DEFAULT NULL COMMENT '卡片标题(为空则用分类名)',
  `article_limit` INT NOT NULL DEFAULT '6' COMMENT '展示文章条数',
  `sort_order` INT NOT NULL DEFAULT '0' COMMENT '排序(越小越靠前)',
  `enable` BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `idx_home_card_sort` (`enable`, `sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页动态卡片配置表';