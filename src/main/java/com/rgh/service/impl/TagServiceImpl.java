package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.Tag;
import com.rgh.mapper.TagMapper;
import com.rgh.service.TagService;
import com.rgh.vo.ElTagVo;
import com.rgh.vo.TagWithCountVo;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import static com.rgh.constant.Constant.El_PLUS_TAGS;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> findAll() {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        return list(queryWrapper);
    }

    @Override
    public List<ElTagVo> getTagList() {
        return findAll().stream().map(tag -> {
            ElTagVo vo = new ElTagVo();
            vo.setId(tag.getId());
            vo.setName(tag.getName());
            vo.setType(El_PLUS_TAGS.get(new SecureRandom().nextInt(5)));
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TagWithCountVo> tagWithCount() {
        return findAll().stream().map(tag -> {
            TagWithCountVo vo = new TagWithCountVo();
            vo.setId(tag.getId());
            vo.setName(tag.getName());
            vo.setCount(0);
            return vo;
        }).collect(Collectors.toList());
    }
}
