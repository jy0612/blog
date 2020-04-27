package com.whpu.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Description: 用户实体类
 * @Author jy
 * @Date 2020/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //id
    private String nickname; //用户昵称
    private String username; //用户名
    private String password; //密码
    private String email; //邮箱
    private String avatar; //头像
    private Integer type; //用户类型
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

//    private List<Blog> blogs; //一对多 一个用户对应多篇文章
}
