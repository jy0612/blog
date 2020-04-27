package com.whpu.blog.dto;


import com.whpu.blog.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName IndexBlog
 * @Description: 首页需要的blog数据
 * @Author jy
 * @Date 2020/4/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexBlog {
    private Integer id; //id
    private String title; //标题
    private String firstPicture; //首图
    private Integer views; //浏览次数
    private Date updateTime; //修改时间
    private String description; //描述

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

    //Tag
    private List<Tag> tags;

}
