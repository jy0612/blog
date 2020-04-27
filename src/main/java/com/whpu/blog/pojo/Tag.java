package com.whpu.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName Tag
 * @Description: 标签实体类
 * @Author jy
 * @Date 2020/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Integer id; //id
    private String name; //标签名称

    private Integer nums; //记录标签数量
//    private List<Blog> blogs; //一个标签对应多篇文章 多个标签对应多个文章 多对多
}
