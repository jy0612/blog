package com.whpu.blog.dao;

import com.whpu.blog.dto.*;
import com.whpu.blog.pojo.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName BlogDao
 * @Description: 完成数据库博客文章操作的接口
 * @Author jy
 * @Date 2020/4/22
 **/
@Mapper
@Repository
public interface BlogDao {

    @Select("select * from tb_blog where id=#{id}")
    Blog selectBlogById(Integer id);

    List<BlogQuery> selectAllBlogs();

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<IndexBlog> selectAllIndexBlogs();

    List<IndexBlog> selectBlogsByQuery(String query);

    DetailedBlog selectDetailedBlogById(Integer id);

    List<IndexBlog> selectIndexBlogsByTypeId(Integer id);

    List<IndexBlog> selectIndexBlogsByTagId(Integer id);

    List<String> selectGroupYear();

    List<AchieveBlog> selectAchievesByYear(String year);

    List<RecommendBlog> selectAllRecommendBlogs();

    @Select("select count(1) from tb_blog")
    Integer selectBlogsCount();

    @Insert("insert into tb_blog_tags values(default,#{blogId},#{tagId})")
    int saveBlogAndTag(BlogAndTag blogAndTag);

    int insertBlog(Blog blog);

    int updateBlog(Blog blog);

    @Update("update tb_blog set views=views+1 where id=#{id}")
    int updateBlogViews(Integer id);

    @Delete("delete from tb_blog where id=#{id}")
    int deleteBlog(Integer id);
}
