package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.HomeCard;
import com.rgh.mapper.HomeCardMapper;
import com.rgh.service.ArticleService;
import com.rgh.service.HomeCardService;
import com.rgh.vo.CateWithArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeCardServiceImpl extends ServiceImpl<HomeCardMapper, HomeCard> implements HomeCardService {

    @Resource
    private ArticleService articleService;

    @Override
    public List<CateWithArticleVo> getRenderCards() {
        List<HomeCard> cards = getEnabledCards();
        List<CateWithArticleVo> result = new ArrayList<>();
        for (HomeCard card : cards) {
            CateWithArticleVo vo = articleService.findCateUnderArticlesById(
                    card.getCategoryId(), card.getArticleLimit());
            if (card.getTitle() != null && !card.getTitle().isEmpty()) {
                vo.setName(card.getTitle());
            }
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<HomeCard> getEnabledCards() {
        LambdaQueryWrapper<HomeCard> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HomeCard::isEnable, true).orderByAsc(HomeCard::getSortOrder);
        return list(wrapper);
    }
}
