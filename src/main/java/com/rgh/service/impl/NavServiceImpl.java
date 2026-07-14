package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rgh.entity.Nav;
import com.rgh.mapper.NavMapper;
import com.rgh.service.NavService;
import com.rgh.util.MakeMenuTreeUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class NavServiceImpl extends ServiceImpl<NavMapper, Nav> implements NavService {

    public List<Nav> getNavs() {
        QueryWrapper<Nav> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        return list(queryWrapper);
    }

    @Override
    public List<Nav> getParentList() {
        //上级菜单，只查type  0 和 1 的
        String[] type = {"0","1"};
        List<String> strings = Arrays.asList(type);
        List<Nav> menus = getNavs().stream().
                filter(f->strings.contains(f.getType()))
                .collect(Collectors.toList());
        //构造树的结构
        //顶级菜单
        Nav menu = new Nav();
        menu.setId(0);
        menu.setParentId(-1);
        menu.setLabel("顶级菜单");
        menus.add(menu);
        return MakeMenuTreeUtil.makeNavTree(menus, -1);
    }

    @Override
    public List<Nav> getNavList() {
        List<Nav> navs = getNavs();
        //构造树的格式数据
        return MakeMenuTreeUtil.makeNavTree(navs, 0);
    }

    @Override
    public Nav insert(Nav nav) {
        boolean saved = save(nav);
        if (saved) {
            return nav; // 插入成功后返回完整的对象，包括自动生成的主键
        } else {
            throw new RuntimeException("插入失败");
        }
    }

    @Override
    public Nav update(Nav nav) {
        boolean updated = save(nav);
        if (updated) {
            return nav; // 更新成功后返回更新后的对象
        } else {
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return removeById(id);
    }


}
