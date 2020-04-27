package com.whpu.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.dto.DetailedBlog;
import com.whpu.blog.dto.IndexBlog;
import com.whpu.blog.dto.RecommendBlog;
import com.whpu.blog.exception.NotFoundException;
import com.whpu.blog.pojo.Tag;
import com.whpu.blog.pojo.Type;
import com.whpu.blog.service.BlogService;
import com.whpu.blog.service.TagService;
import com.whpu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description: index控制器
 * @Author jy
 * @Date 2020/4/20
 **/
@Controller
public class IndexController {

    private static final Integer pageSize = 5;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
    * @MethodName: index
    * @Description: index页面跳转
    * @Param: []
    * @Return: java.lang.String
    **/
    @GetMapping("/")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<IndexBlog> indexBlogList = blogService.getAllIndexBlogs();
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogList);
        List<Type> typeList = typeService.getAllTypesByNums();
        List<Tag> tagList = tagService.getAllTagsByNums();
        List<RecommendBlog> recommendList = blogService.getAllRecommendBlogs();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("recommendList",recommendList);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,String query,Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<IndexBlog> queryList = blogService.getBlogsByQuery(query);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(queryList);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id,Model model){
        DetailedBlog detailedBlog = blogService.getDetailedBlogById(id);
        model.addAttribute("blog",detailedBlog);
        return "blog";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
