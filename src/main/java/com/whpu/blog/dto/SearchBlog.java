package com.whpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SearchBlog
 * @Description: 博客列表页进行多条件查询时所使用的类
 * @Author jy
 * @Date 2020/4/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBlog {
    private String title;
    private Integer typeId;
    private boolean recommend;
}
