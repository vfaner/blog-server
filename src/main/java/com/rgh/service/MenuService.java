package com.rgh.service;

import com.rgh.entity.Menu;
import com.rgh.vo.RouterVo;

import java.util.List;

public interface MenuService {

    List<Menu> getMenuList();

    List<RouterVo> getMenuRouterList();
}
