package com.whpu.blog.service;

import com.whpu.blog.dto.*;
import com.whpu.blog.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName BlogService
 * @Description: 博客文章服务层接口
 * @Author jy
 * @Date 2020/4/22
 **/
public interface BlogService {

    Blog getBlog(Integer id);

    List<BlogQuery> getAllBlogs();

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    List<IndexBlog> getAllIndexBlogs();

    List<IndexBlog> getBlogsByQuery(String query);

    DetailedBlog getDetailedBlogById(Integer id);

    List<IndexBlog> getIndexBlogsByTypeId(Integer id);

    List<IndexBlog> getIndexBlogsByTagId(Integer id);

    Map<String,List<AchieveBlog>> getAchievesByYear();

    List<RecommendBlog> getAllRecommendBlogs();

    Integer getBlogsCount();

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Integer id);
}
