package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.HomeCard;
import com.rgh.vo.CateWithArticleVo;

import java.util.List;

public interface HomeCardService extends IService<HomeCard> {
    List<CateWithArticleVo> getRenderCards();
    List<HomeCard> getEnabledCards();
}
