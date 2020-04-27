package com.whpu.blog.dto;

import com.whpu.blog.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DetailedBlog
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailedBlog {
    private Integer id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private Date updateTime;

    private String nickname;
    private String avatar;

    private String typeName;

    private List<Tag> tags;

}
