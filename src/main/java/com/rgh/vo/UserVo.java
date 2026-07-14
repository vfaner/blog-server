package com.rgh.vo;

import com.rgh.entity.User;

import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 用户展示查询
 */
@Data
public class UserVo {
   private List<User> content;
   private Integer total;
   private Integer pageNum = 0;
   private Integer pageSize = 10;
}
