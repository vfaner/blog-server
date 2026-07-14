package com.rgh.util;

import com.rgh.entity.Menu;
import com.rgh.entity.Nav;
import com.rgh.vo.RouterVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MakeMenuTreeUtil {
    public static List<Menu> makeTree(List<Menu> menuList, Integer pid){
        List<Menu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && Objects.equals(item.getParentId(), pid))
                .forEach(item ->{
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(item,menu);
                    //获取每一个item的下级
                    List<Menu> children = makeTree(menuList, item.getId());
                    menu.setChildren(children);
                    list.add(menu);
                });
        return list;
    }

    public static List<RouterVo> makeRouter(List<Menu> menuList, Integer pid){
        List<RouterVo> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && Objects.equals(item.getParentId(), pid))
                .forEach(item -> {
                    RouterVo router = new RouterVo();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    if(0 == item.getParentId()){
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    }else{
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }
                    //设置meta
                    router.setMeta(new RouterVo.Meta(
                            item.getLabel(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    //设置children,每一项的下级

                    List<RouterVo> children = makeRouter(menuList, item.getId());
                    router.setChildren(children);

                    if(!router.getChildren().isEmpty()){
                        router.setAlwaysShow(true);
                    }
                    list.add(router);
                });
        return list;
    }
    public static List<Nav> makeNavTree(List<Nav> menuList, Integer pid){
        List<Nav> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && Objects.equals(item.getParentId(), pid))
                .forEach(item ->{
                    Nav menu = new Nav();
                    BeanUtils.copyProperties(item,menu);
                    //获取每一个item的下级
                    List<Nav> children = makeNavTree(menuList, item.getId());
                    menu.setChildren(children);
                    list.add(menu);
                });
        return list;
    }

    public static List<RouterVo> makeNavRouter(List<Nav> menuList, Integer pid){
        List<RouterVo> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && Objects.equals(item.getParentId(), pid))
                .forEach(item -> {
                    RouterVo router = new RouterVo();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    if(0 == item.getParentId()){
                        router.setComponent("Common");
                        router.setAlwaysShow(true);
                    }else{
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }
                    //设置meta
                    router.setMeta(new RouterVo.Meta(
                            item.getLabel(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    //设置children,每一项的下级

                    List<RouterVo> children = makeNavRouter(menuList, item.getId());
                    router.setChildren(children);

                    if(!router.getChildren().isEmpty()){
                        router.setAlwaysShow(true);
                    }
                    list.add(router);
                });
        return list;
    }

}