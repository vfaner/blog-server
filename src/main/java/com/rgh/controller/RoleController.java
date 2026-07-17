package com.rgh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rgh.constant.Constant;
import com.rgh.entity.Menu;
import com.rgh.entity.Role;
import com.rgh.entity.RoleMenu;
import com.rgh.mapper.RoleMenuMapper;
import com.rgh.service.impl.MenuServiceImpl;
import com.rgh.service.impl.RoleServiceImpl;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Role)表控制层
 *
 * @author rgh @mail 817094@qq.com
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/role")
@Tag(name = "角色数据接口", description = "角色数据操作接口")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Operation(summary = "角色分页")
    @GetMapping("/page-query")
    public ResultUtil pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String name) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) qw.like("name", name);
        qw.orderByAsc("id");
        Page<Role> page = new Page<>(pageNum, pageSize);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, roleService.page(page, qw));
    }

    @Operation(summary = "角色列表")
    @GetMapping("/query")
    public ResultUtil query() {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, roleService.list());
    }

    @Operation(summary = "新增角色")
    @PostMapping
    public ResultUtil add(@RequestBody Role role) {
        roleService.save(role);
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, role);
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public ResultUtil update(@RequestBody Role role) {
        roleService.updateById(role);
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, role);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    @Transactional
    public ResultUtil delete(@PathVariable Integer id) {
        // 顺带清空该角色的菜单关联，避免脏数据
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", id));
        roleService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }

    /**
     * 返回菜单权限树 + 该角色已勾选的菜单 id 列表
     * 响应结构：{ listmenu: [菜单树], checkList: [叶子节点 id 列表] }
     */
    @Operation(summary = "分配权限树")
    @GetMapping("/getAssignPermissionTree")
    public ResultUtil assignTree(@RequestParam(required = false) Integer roleId) {
        // 1. 加载所有菜单，按 orderNum 升序
        List<Menu> sorted = menuService.list().stream()
                .sorted(Comparator.comparing(m -> m.getOrderNum() == null ? 0 : m.getOrderNum()))
                .collect(Collectors.toList());
        // 2. 构建父->子映射
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (Menu m : sorted) {
            TreeNode node = new TreeNode();
            node.setId(m.getId());
            node.setLabel(m.getLabel());
            node.setParentId(m.getParentId());
            node.setChildren(new ArrayList<>());
            nodeMap.put(m.getId(), node);
        }
        List<TreeNode> tree = new ArrayList<>();
        for (TreeNode node : nodeMap.values()) {
            if (node.getParentId() == null || node.getParentId() == 0) {
                tree.add(node);
            } else {
                TreeNode parent = nodeMap.get(node.getParentId());
                if (parent != null) parent.getChildren().add(node);
                else tree.add(node); // 孤儿节点作为顶级
            }
        }
        tree.sort(Comparator.comparing(n -> n.getId() == null ? 0 : n.getId()));

        // 3. 查该角色已勾选的菜单 id
        List<Integer> checkList = new ArrayList<>();
        if (roleId != null) {
            List<Integer> ids = roleMenuMapper.findMenuIdsByRoleId(roleId);
            if (ids != null) checkList.addAll(ids);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("listmenu", tree);
        data.put("checkList", checkList);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, data);
    }

    /** 保存角色-菜单关联：先清空再重建 */
    @Operation(summary = "保存分配权限")
    @PostMapping("/roleAssignSave")
    @Transactional
    public ResultUtil assignSave(@RequestBody AssignSaveDto body) {
        if (body.getRoleId() == null) return ResultUtil.fail(500, "角色 id 不能为空");
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", body.getRoleId()));
        if (body.getList() != null && !body.getList().isEmpty()) {
            for (Object mid : body.getList()) {
                if (mid == null) continue;
                Integer menuId;
                try { menuId = Integer.valueOf(mid.toString()); }
                catch (NumberFormatException e) { continue; }
                RoleMenu rm = new RoleMenu();
                rm.setRoleId(body.getRoleId());
                rm.setMenuId(menuId);
                roleMenuMapper.insert(rm);
            }
        }
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS);
    }

    /** 用于树节点响应 */
    @lombok.Data
    public static class TreeNode {
        private Integer id;
        private String label;
        private Integer parentId;
        private List<TreeNode> children;
    }

    /** 保存分配权限的请求体 */
    @lombok.Data
    public static class AssignSaveDto {
        private Integer roleId;
        private List<Object> list;
    }
}
