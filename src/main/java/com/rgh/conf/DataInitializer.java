package com.rgh.conf;

import com.rgh.entity.User;
import com.rgh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动时数据初始化：
 * 1. 补齐 rgh_article 的下载相关字段（兼容老库）
 * 2. 确保 admin 密码已 BCrypt 加密
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        ensureColumns();
        fixMenus();
        fixOrphanReplies();
        resetAdminPassword();
    }

    /** 修复历史数据：回复评论若 article_id 为 null，从父评论继承 */
    private void fixOrphanReplies() {
        try {
            int updated = jdbcTemplate.update(
                "UPDATE rgh_comment c INNER JOIN rgh_comment p ON c.parent_id = p.uid " +
                "SET c.article_id = p.article_id " +
                "WHERE c.article_id IS NULL AND p.article_id IS NOT NULL");
            if (updated > 0) System.out.println(">>> 已修正 " + updated + " 条孤立回复评论的 article_id");
        } catch (Exception e) {
            System.err.println(">>> 修正回复评论失败: " + e.getMessage());
        }
    }

    /** 修正菜单：统一编写文章路径 + 去重 */
    private void fixMenus() {
        try {
            // 统一 id=12 的编写文章路径
            jdbcTemplate.update("UPDATE rgh_menu SET path = '/admin/article-edit' WHERE id = 12");
            // 删除路径为旧值 /admin/article/edit 的重复菜单（保留 id=12）
            jdbcTemplate.update("DELETE FROM rgh_menu WHERE path = '/admin/article/edit' AND id <> 12");
            // 删除同名同路径的重复菜单（保留最小 id）
            jdbcTemplate.update(
                "DELETE m1 FROM rgh_menu m1 " +
                "INNER JOIN rgh_menu m2 " +
                "WHERE m1.id > m2.id AND m1.path = m2.path AND m1.path <> '' AND m1.path IS NOT NULL");
            System.out.println(">>> 菜单数据已修正/去重");
        } catch (Exception e) {
            System.err.println(">>> 修正菜单失败: " + e.getMessage());
        }
    }

    /** 动态补齐缺失的列（MySQL 版本无关，先查 information_schema 再 ADD） */
    private void ensureColumns() {
        Map<String, String> cols = new HashMap<>();
        cols.put("download_enable", "BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否开启下载'");
        cols.put("download_name", "VARCHAR(255) DEFAULT NULL COMMENT '附件名称'");
        cols.put("download_url", "VARCHAR(500) DEFAULT NULL COMMENT '下载地址'");
        cols.put("download_size", "VARCHAR(50) DEFAULT NULL COMMENT '附件大小'");
        cols.put("download_desc", "VARCHAR(500) DEFAULT NULL COMMENT '附件描述'");
        cols.put("view_count", "INT NOT NULL DEFAULT 0 COMMENT '阅读量'");
        cols.put("like_count", "INT NOT NULL DEFAULT 0 COMMENT '点赞数'");

        cols.forEach((col, def) -> {
            try {
                Integer exists = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM information_schema.COLUMNS " +
                        "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'rgh_article' AND COLUMN_NAME = ?",
                        Integer.class, col);
                if (exists != null && exists == 0) {
                    jdbcTemplate.execute("ALTER TABLE rgh_article ADD COLUMN " + col + " " + def);
                    System.out.println(">>> 已添加列 rgh_article." + col);
                }
            } catch (Exception e) {
                System.err.println(">>> 检查/添加列 " + col + " 失败: " + e.getMessage());
            }
        });

        // rgh_user 表补 email 列
        try {
            Integer exists = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM information_schema.COLUMNS " +
                    "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'rgh_user' AND COLUMN_NAME = 'email'",
                    Integer.class);
            if (exists != null && exists == 0) {
                jdbcTemplate.execute("ALTER TABLE rgh_user ADD COLUMN email VARCHAR(255) DEFAULT NULL COMMENT '邮箱'");
                System.out.println(">>> 已添加列 rgh_user.email");
            }
        } catch (Exception e) {
            System.err.println(">>> 检查/添加列 rgh_user.email 失败: " + e.getMessage());
        }
    }

    private void resetAdminPassword() {
        User admin = userService.findByUsername("admin");
        if (admin != null) {
            admin.setPassword(passwordEncoder.encode("123456"));
            userService.updateById(admin);
            System.out.println(">>> Admin password reset to BCrypt(123456)");
        }
    }
}
