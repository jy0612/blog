package com.whpu.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName Type
 * @Description: 分类实体类
 * @Author jy
 * @Date 2020/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Integer id; //id
    @NotBlank(message = "分类名称不能为空")
    private String name; //分类名称

    private Integer nums; //记录分类数量
//    private List<Blog> blogs; //一对多 一种类型对应多篇文章
}
