package com.whpu.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Blog
 * @Description: 博客文章实体类
 * @Author jy
 * @Date 2020/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id; //id
    private String title; //标题
    private String content; //文章内容
    private String firstPicture; //首图
    private String flag; //标记，原创，转载，翻译
    private Integer views; //浏览次数
    private boolean appreciation; //赞赏是否开启
    private boolean shareStatement; //转载声明是否开启
    private boolean commentable; //评论是否开启
    private boolean recommend; //是否推荐
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private String description; //描述
    private Integer typeId; //分类id
    private Integer userId; //用户id
    private String tagIds; //新增时取得选中的多个标签id

//    private Type type; //一篇文章分一类 多篇文章也可归为一类
//    private List<Tag> tags; //一篇文章有多个标签 多个标签多个文章 多对多
//    private User user; //一篇文章对应一个作者 多个文章对应一个作者 多对一
//    private List<Comment> comments; //一对多 一篇文章多个评论
}
