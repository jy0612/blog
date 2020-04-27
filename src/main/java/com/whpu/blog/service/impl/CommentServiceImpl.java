package com.whpu.blog.service.impl;

import com.whpu.blog.dao.CommentDao;
import com.whpu.blog.pojo.Comment;
import com.whpu.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/25
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentsByBlogId(Integer blogId) {
        List<Comment> parentComments = commentDao.selectCommentsByBlogIdAndParentNoId(blogId);
        for (Comment parent : parentComments){
            parent.setChildComments(commentDao.selectChildrenByBlogIdAndParentId(blogId,parent.getId()));
        }
        return parentComments;
    }

    @Override
    @Transactional
    public Integer saveComment(Comment comment) {
        Integer parentCommendId = comment.getParentComment().getId();
        if(parentCommendId != -1){
            comment.setParentComment(commentDao.selectCommentById(parentCommendId));
        }else{
            comment.getParentComment().setId(null);
        }
        return commentDao.insertComment(comment);
    }
}
