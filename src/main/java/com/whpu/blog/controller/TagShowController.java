package com.whpu.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.dto.IndexBlog;
import com.whpu.blog.pojo.Tag;
import com.whpu.blog.service.BlogService;
import com.whpu.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName TagShowController
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/26
 **/
@Controller
public class TagShowController {

    private static final Integer pageSize = 5;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Integer id, @RequestParam(value = "pageNum",defaultValue = "5") Integer pageNum, Model model){
        List<Tag> tagList = tagService.getAllTagsIncludeNums();
        if(id == -1){
            id = tagList.get(0).getId();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<IndexBlog> blogList = blogService.getIndexBlogsByTagId(id);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";
    }

}
