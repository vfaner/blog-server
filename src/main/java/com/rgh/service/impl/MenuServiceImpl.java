package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rgh.entity.Menu;
import com.rgh.mapper.MenuMapper;
import com.rgh.service.MenuService;
import com.rgh.util.MakeMenuTreeUtil;
import com.rgh.vo.RouterVo;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    public List<Menu> getMenus() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        return list(queryWrapper);
    }

    @Override
    public List<Menu> getMenuList() {
        final List<Menu> all = getMenus();
        //构造树的格式数据
        return MakeMenuTreeUtil.makeTree(all,0);
    }
    @Override
    public List<RouterVo> getMenuRouterList() {
        final List<Menu> all = getMenus();
        //构造树的格式数据
        return MakeMenuTreeUtil.makeRouter(all,0);
    }
}
