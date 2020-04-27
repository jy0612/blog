package com.whpu.blog.dao;

import com.whpu.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName CommentDao
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/25
 **/
@Mapper
@Repository
public interface CommentDao {

    Comment selectCommentById(Integer id);

    List<Comment> selectCommentsByBlogIdAndParentNoId(Integer blogId);

    List<Comment> selectChildrenByBlogIdAndParentId(Integer blogId,Integer parentId);

    Integer insertComment(Comment comment);

}
