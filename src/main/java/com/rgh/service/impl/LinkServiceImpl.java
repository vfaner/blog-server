package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.Link;
import com.rgh.mapper.LinkMapper;
import com.rgh.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public IPage<Link> getPageLinks(Integer current, Integer size, String name, String link, Boolean isEnable) {
        QueryWrapper<Link> qw = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) qw.like("name", name);
        if (link != null && !link.isEmpty()) qw.like("link", link);
        if (isEnable != null) qw.eq("is_enable", isEnable);
        qw.orderByAsc("id");
        Page<Link> page = new Page<>(current, size);
        return page(page, qw);
    }

    @Override
    public List<Link> getAllEnabledLinks() {
        QueryWrapper<Link> qw = new QueryWrapper<>();
        qw.eq("is_enable", true);
        return list(qw);
    }
}
