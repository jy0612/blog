package com.whpu.blog.service.impl;

import com.whpu.blog.dao.BlogDao;
import com.whpu.blog.dto.*;
import com.whpu.blog.exception.NotFoundException;
import com.whpu.blog.pojo.Blog;
import com.whpu.blog.service.BlogService;
import com.whpu.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogServiceImpl
 * @Description: 博客文章服务层实现类
 * @Author jy
 * @Date 2020/4/22
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Integer id) {
        return blogDao.selectBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogDao.selectAllBlogs();
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public List<IndexBlog> getAllIndexBlogs() {
        return blogDao.selectAllIndexBlogs();
    }

    @Override
    public List<IndexBlog> getBlogsByQuery(String query) {
        if(query==null){
            query = "";
        }
        return blogDao.selectBlogsByQuery(query);
    }

    @Override
    @Transactional
    public DetailedBlog getDetailedBlogById(Integer id) {
        DetailedBlog detailedBlog = blogDao.selectDetailedBlogById(id);
        if(detailedBlog==null){
            throw new NotFoundException("该文章不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogDao.updateBlogViews(detailedBlog.getId());
        return detailedBlog;
    }

    @Override
    public List<IndexBlog> getIndexBlogsByTypeId(Integer id) {
        return blogDao.selectIndexBlogsByTypeId(id);
    }

    @Override
    public List<IndexBlog> getIndexBlogsByTagId(Integer id) {
        return blogDao.selectIndexBlogsByTagId(id);
    }


    @Override
    public Map<String,List<AchieveBlog>> getAchievesByYear() {
        Map<String,List<AchieveBlog>> map = new LinkedHashMap<>();
        List<String> years = blogDao.selectGroupYear();
        for(String year : years){
            map.put(year,blogDao.selectAchievesByYear(year));
        }
        return map;
    }

    @Override
    public List<RecommendBlog> getAllRecommendBlogs() {
        return blogDao.selectAllRecommendBlogs();
    }

    @Override
    public Integer getBlogsCount() {
        return blogDao.selectBlogsCount();
    }

    @Transactional
    @Override
    public int saveBlogAndTag(BlogAndTag blogAndTag) {
        return blogDao.saveBlogAndTag(blogAndTag);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);

        return blogDao.insertBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Integer id) {
        return blogDao.deleteBlog(id);
    }
}
