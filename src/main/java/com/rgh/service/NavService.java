package com.rgh.service;

import com.rgh.entity.Nav;
import java.util.List;

public interface NavService {
    List<Nav> getParentList();

    List<Nav> getNavList();

    Nav insert(Nav nav);

    Nav update(Nav nav);

    boolean deleteById(Integer id);
}

