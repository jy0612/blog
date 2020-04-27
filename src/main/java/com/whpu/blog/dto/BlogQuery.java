package com.whpu.blog.dto;

import com.whpu.blog.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName BlogQuery
 * @Description: 博客列表页显示数据所使用的类
 * @Author jy
 * @Date 2020/4/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {

    private Integer id;
    private String title;
    private Date updateTime;
    private boolean recommend;
    private Integer typeId;

    private Type type;
}
