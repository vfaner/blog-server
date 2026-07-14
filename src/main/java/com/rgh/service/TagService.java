package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.Tag;
import com.rgh.vo.ElTagVo;
import com.rgh.vo.TagWithCountVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> findAll();
    List<ElTagVo> getTagList();
    List<TagWithCountVo> tagWithCount();
}
