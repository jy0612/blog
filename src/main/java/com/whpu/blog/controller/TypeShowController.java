package com.whpu.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.dto.IndexBlog;
import com.whpu.blog.pojo.Type;
import com.whpu.blog.service.BlogService;
import com.whpu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName TypeShowController
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/26
 **/
@Controller
public class TypeShowController {

    private static final Integer pageSize = 5;

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Integer id, Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        List<Type> typeList = typeService.getAllTypesByNums();
        if (id == -1) {
            id = typeList.get(0).getId();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<IndexBlog> blogList = blogService.getIndexBlogsByTypeId(id);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }


}
