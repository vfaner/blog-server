-- ============================================
-- 菜单数据
-- ============================================
INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (1, 0, '系统管理', 'sys:manage', '/system', 'system', '', 1, 0, 'Setting', '2023-08-08 11:11:11', '2023-08-09 15:26:28');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (2, 1, '网站设置', 'sys:setting', '/admin/setting', 'setting', '/system/setting/SystemSetting', 0, 1, 'ElemeFilled', '2024-12-15 16:50:40', '2024-12-15 16:50:40');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (3, 1, '菜单导航', 'sys:menu', '/admin/menu', 'menu', '/system/menu/MenuList', 0, 1, 'Menu', '2024-12-15 17:12:16', '2024-12-15 17:12:16');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (4, 1, '友情链接', 'sys:link', '/admin/link', 'link', '/system/link/LinkList', 0, 1, 'Link', '2024-12-15 17:16:00', '2024-12-15 17:16:00');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (5, 1, '角色管理', 'sys:role', '/admin/role', 'role', '/system/role/RoleList', 0, 1, 'CreditCard', '2024-12-15 17:19:34', '2024-12-15 17:19:34');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (6, 1, '用户管理', 'sys:user', '/admin/user', 'user', '/system/user/UserList', 0, 1, 'User', '2024-12-15 17:20:48', '2024-12-15 17:20:48');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (7, 0, '系统工具', 'sys:log', '/admin/log', 'log', '', 3, 0, 'ChatLineSquare', '2024-12-15 17:23:16', '2024-12-15 17:23:16');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (8, 7, '操作日志', 'sys:oper', '/admin/operator', 'operator', '/system/config/SystemLog', 2, 1, 'Operation', '2024-12-15 17:26:09', '2024-12-15 17:26:09');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (9, 7, '接口文档', 'sys:api', '/admin/api', 'api', '/system/config/SystemDocument', 1, 1, 'DocumentCopy', '2024-12-15 17:26:43', '2024-12-15 17:26:43');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (10, 0, '文章管理', 'sys:article', '/admin/article', 'article', '', 2, 0, 'Discount', '2024-12-15 17:29:14', '2024-12-15 17:29:14');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (11, 10, '所有文章', 'sys:articles', '/admin/articles', 'articles', '/articles/article/ArticleList', 1, 1, 'Reading', '2024-12-15 17:35:44', '2024-12-15 17:35:44');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (12, 10, '编写文章', 'sys:articles:edit', '/admin/article-edit', 'articleEdit', '/articles/article/EditArticle', 2, 1, 'Edit', '2024-12-15 17:38:39', '2024-12-15 17:38:39');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (13, 10, '分类管理', 'sys:cate', '/admin/cate', 'cate', '/articles/category/CategoryList', 3, 1, 'SetUp', '2024-12-15 19:12:07', '2024-12-15 19:12:07');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (14, 10, '标签管理', 'sys:tag', '/admin/tag', 'tag', '/articles/tag/TagList', 4, 1, 'Pointer', '2024-12-15 19:40:30', '2024-12-15 19:40:30');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (15, 10, '评论管理', 'sys:comment', '/admin/comment', 'comment', '/articles/comment/CommentList', 5, 1, 'ChatDotSquare', '2025-07-12 10:00:00', '2025-07-12 10:00:00');

INSERT IGNORE INTO `rgh_menu` (`id`, `parent_id`, `label`, `code`, `path`, `name`, `url`, `order_num`, `type`, `icon`, `create_time`, `update_time`)
VALUES (16, 1, '首页卡片', 'sys:homecard', '/admin/home-card', 'homeCard', '/system/setting/HomeCard', 1, 1, 'Postcard', '2025-07-12 10:00:00', '2025-07-12 10:00:00');

-- ============================================
-- 用户数据
-- ============================================
INSERT IGNORE INTO rgh_user (username, password, avatar, role, nick_name)
VALUES ('admin', '123456', '/src/assets/avatar.jpg', 'root', '管理员');

-- ============================================
-- 角色数据
-- ============================================
INSERT IGNORE INTO rgh_role (id, name, code, description) VALUES
    (1, '管理员', 'admin', '超级管理员'),
    (2, '作者', 'author', '编写文章发布文章'),
    (3, '访客', 'view', '普通用户浏览文章');

-- ============================================
-- 用户角色关联
-- ============================================
INSERT IGNORE INTO rgh_user_role (id, user_id, role_id) VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3);

-- ============================================
-- 分类数据
-- ============================================
INSERT IGNORE INTO rgh_category (id, name, alias, icon, enable, description) VALUES
    (1, 'Java', 'java', NULL, 1, 'Java编程相关文章'),
    (2, '前端', 'frontend', NULL, 1, '前端技术文章'),
    (3, '数据库', 'database', NULL, 1, '数据库相关文章'),
    (4, '工具软件', 'tools', NULL, 1, '实用工具软件分享'),
    (5, '其他', 'other', NULL, 1, '其他分类');

-- ============================================
-- 标签数据
-- ============================================
INSERT IGNORE INTO rgh_tag (id, name, alias, description) VALUES
    (1, 'Spring Boot', 'springboot', 'Spring Boot框架'),
    (2, 'Vue', 'vue', 'Vue.js前端框架'),
    (3, 'MySQL', 'mysql', 'MySQL数据库'),
    (4, 'Docker', 'docker', 'Docker容器'),
    (5, 'Git', 'git', 'Git版本控制'),
    (6, 'TypeScript', 'typescript', 'TypeScript语言');

-- ============================================
-- 文章数据
-- ============================================
INSERT IGNORE INTO rgh_article (id, title, cover, author, content, state, top, create_time, update_time) VALUES
    (1, 'Spring Boot 入门教程', NULL, 'admin', '<h2>Spring Boot 入门</h2><p>Spring Boot 是一个基于 Spring 框架的快速开发工具，它简化了 Spring 应用的初始搭建和开发过程。</p><p>通过自动配置和起步依赖，开发者可以快速创建独立的、生产级别的 Spring 应用。</p>', b'1', b'1', '2024-12-01 10:00:00', '2024-12-15 10:00:00'),
    (2, 'Vue3 + TypeScript 实战', NULL, 'admin', '<h2>Vue3 实战</h2><p>Vue3 带来了 Composition API，配合 TypeScript 可以让代码更加类型安全和易于维护。</p><p>本文将带你从零开始搭建一个 Vue3 + TypeScript + Vite 的项目。</p>', b'1', b'0', '2024-12-05 14:00:00', '2024-12-14 14:00:00'),
    (3, 'MySQL 索引优化指南', NULL, 'admin', '<h2>MySQL 索引优化</h2><p>索引是提高 MySQL 查询性能的重要手段。本文将介绍索引的类型、原理以及优化技巧。</p>', b'1', b'0', '2024-12-08 09:00:00', '2024-12-13 09:00:00'),
    (4, 'Docker 容器化部署实践', NULL, 'admin', '<h2>Docker 部署实践</h2><p>Docker 让应用的打包和部署变得简单。本文将演示如何将一个 Spring Boot 应用容器化。</p>', b'1', b'0', '2024-12-10 16:00:00', '2024-12-12 16:00:00'),
    (5, 'Git 工作流程最佳实践', NULL, 'admin', '<h2>Git 工作流</h2><p>规范的 Git 工作流程对团队协作至关重要。本文介绍 Git Flow 和 GitHub Flow 两种主流工作流。</p>', b'1', b'0', '2024-12-11 11:00:00', '2024-12-15 11:00:00'),
    (6, 'Element Plus 组件库详解', NULL, 'admin', '<h2>Element Plus 详解</h2><p>Element Plus 是基于 Vue3 的组件库，提供了丰富的 UI 组件。本文将深入介绍其常用组件。</p>', b'1', b'0', '2024-12-12 08:00:00', '2024-12-15 08:00:00'),
    (7, 'Spring Security 整合 JWT', NULL, 'admin', '<h2>Spring Security + JWT</h2><p>JWT（JSON Web Token）是一种无状态的认证方案。本文介绍如何在 Spring Boot 中整合 Spring Security 和 JWT。</p>', b'1', b'0', '2024-12-13 15:00:00', '2024-12-15 15:00:00'),
    (8, 'Vite 构建工具深度解析', NULL, 'admin', '<h2>Vite 构建工具</h2><p>Vite 是新一代前端构建工具，利用浏览器原生 ES Module 实现极速开发体验。</p>', b'1', b'0', '2024-12-14 12:00:00', '2024-12-15 12:00:00');

-- ============================================
-- 文章-分类关联
-- ============================================
INSERT IGNORE INTO rgh_article_category (id, article_id, category_id) VALUES
    (1, 1, 1), (2, 2, 2), (3, 3, 3), (4, 4, 1), (5, 4, 4),
    (6, 5, 4), (7, 6, 2), (8, 7, 1), (9, 8, 2);

-- ============================================
-- 文章-标签关联
-- ============================================
INSERT IGNORE INTO rgh_article_tag (id, article_id, tag_id) VALUES
    (1, 1, 1), (2, 2, 2), (3, 2, 6), (4, 3, 3),
    (5, 4, 1), (6, 4, 4), (7, 5, 5), (8, 6, 2),
    (9, 7, 1), (10, 8, 2);

-- ============================================
-- 评论数据（已审核通过）
-- ============================================
INSERT IGNORE INTO rgh_comment (uid, parent_id, username, article_id, avatar, link, address, content, level, `like`, state, created_time) VALUES
    (1, NULL, '游客小明', 1, '/src/assets/avatar.jpg', '', '北京', '写得很好，学习了！', 1, 5, 1, '2024-12-15 10:30:00'),
    (2, 1, 'admin', 1, '/src/assets/avatar.jpg', '', '上海', '感谢支持，有问题欢迎交流', 2, 2, 1, '2024-12-15 11:00:00'),
    (3, NULL, '前端爱好者', 2, '/src/assets/avatar.jpg', '', '深圳', 'Vue3 + TS 确实很好用', 1, 3, 1, '2024-12-15 14:30:00'),
    (4, NULL, 'DBA老王', 3, '/src/assets/avatar.jpg', '', '广州', '索引优化确实很重要，建议加上explain分析', 1, 4, 1, '2024-12-15 16:00:00');

-- ============================================
-- 友情链接
-- ============================================
INSERT IGNORE INTO rgh_link (id, name, link, description, is_enable) VALUES
    (1, 'Spring 官网', 'https://spring.io', 'Spring 官方文档', b'1'),
    (2, 'Vue.js', 'https://vuejs.org', 'Vue.js 官网', b'1'),
    (3, 'GitHub', 'https://github.com', '代码托管平台', b'1');

-- ============================================
-- 首页卡片配置
-- ============================================
INSERT IGNORE INTO rgh_home_card (id, category_id, title, article_limit, sort_order, enable) VALUES
    (1, 1, 'Java技术', 6, 1, b'1'),
    (2, 2, '前端开发', 6, 2, b'1'),
    (3, 3, '数据库', 4, 3, b'1');

-- ============================================
-- 前端导航数据
-- ============================================
INSERT IGNORE INTO rgh_nav (id, parent_id, label, code, path, name, url, order_num, type, icon, enable, create_time, update_time) VALUES
    (1, 0, '首页', 'home', '/index', 'home', '', 1, 1, 'fa fa-home', b'1', '2024-12-15 10:00:00', '2024-12-15 10:00:00'),
    (2, 0, '文章搜索', 'search', '/articles', 'articleSearch', '', 2, 1, 'fa fa-search', b'1', '2024-12-15 10:00:00', '2024-12-15 10:00:00'),
    (3, 0, 'Java', 'cate_java', '/category/1', 'cateJava', '', 3, 1, 'fa fa-coffee', b'1', '2024-12-15 10:00:00', '2024-12-15 10:00:00'),
    (4, 0, '前端', 'cate_front', '/category/2', 'cateFront', '', 4, 1, 'fa fa-code', b'1', '2024-12-15 10:00:00', '2024-12-15 10:00:00'),
    (5, 0, '数据库', 'cate_db', '/category/3', 'cateDb', '', 5, 1, 'fa fa-database', b'1', '2024-12-15 10:00:00', '2024-12-15 10:00:00');

-- ============================================
-- 修正已存在数据（INSERT IGNORE 不会更新已有行）
-- ============================================
UPDATE rgh_menu SET path = '/admin/article-edit' WHERE id = 12;
