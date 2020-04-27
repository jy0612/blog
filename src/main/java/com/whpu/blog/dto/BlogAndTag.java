package com.whpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BlogAndTags
 * @Description: 把博客和标签关系存到数据库中使用的类
 * @Author jy
 * @Date 2020/4/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {

    private Integer tagId;

    private Integer blogId;
}
