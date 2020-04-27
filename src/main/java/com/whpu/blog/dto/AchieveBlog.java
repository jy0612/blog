package com.whpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName RecommendBlog
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchieveBlog {

    private Integer id;
    private String title;
    private String flag;
    private Date createTime;
}

