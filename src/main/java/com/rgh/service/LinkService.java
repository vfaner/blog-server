package com.rgh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.Link;

import java.util.List;

public interface LinkService extends IService<Link> {
    IPage<Link> getPageLinks(Integer current, Integer size, String name, String link, Boolean isEnable);
    List<Link> getAllEnabledLinks();
}
