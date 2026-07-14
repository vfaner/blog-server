package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> findAll();
}
