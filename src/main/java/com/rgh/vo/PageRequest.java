package com.rgh.vo;

import com.rgh.constant.Constant;
import lombok.Data;

@Data
public class PageRequest {

    private int pageNum = Constant.DEFAULT_PAGE_NUM;

    private int pageSize = Constant.DEFAULT_PAGE_SIZE;

    public PageRequest() {}
    public PageRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
