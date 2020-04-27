package com.whpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RecommendBlog
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendBlog {

    private Integer id;
    private String title;
    private boolean recommend;
}
