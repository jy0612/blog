package com.whpu.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Comment
 * @Description: 评论实体类
 * @Author jy
 * @Date 2020/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id; //id
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //评论内容
    private String avatar; //头像
    private Date createTime; //评论时间
    private Integer blogId; //blogID

    private boolean adminComment; //判断是否为管理员评论

    private Comment parentComment; //父评论
    private List<Comment> childComments; //子评论 一个父评论下面有多个子评论
}
