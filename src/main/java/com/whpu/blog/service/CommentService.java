package com.whpu.blog.service;

import com.whpu.blog.pojo.Comment;

import java.util.List;

/**
 * @InterfaceName CommentService
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/25
 **/
public interface CommentService {

    List<Comment> getCommentsByBlogId(Integer blogId);

    Integer saveComment(Comment comment);
}
