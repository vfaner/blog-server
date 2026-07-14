package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.Menu;
import com.rgh.service.MenuService;
import com.rgh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constant.PATH_PREFIX + "/menu")
public class MenuController {
    @Autowired
    private MenuService service;
    @RequestMapping("/getMenu")
    public ResultUtil getMenu(){
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, service.getMenuRouterList());
    }
}
