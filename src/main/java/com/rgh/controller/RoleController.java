package com.rgh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rgh.constant.Constant;
import com.rgh.entity.Role;
import com.rgh.service.impl.RoleServiceImpl;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultUtil delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }

    @Operation(summary = "分配权限树")
    @GetMapping("/getAssignPermissionTree")
    public ResultUtil assignTree(@RequestParam(required = false) Integer roleId) {
        // 暂返回空树，需要时再实现菜单权限树
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, new java.util.ArrayList<>());
    }

    @Operation(summary = "保存分配权限")
    @PostMapping("/roleAssignSave")
    public ResultUtil assignSave(@RequestBody Object param) {
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS);
    }
}
