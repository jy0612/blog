package com.whpu.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.dto.BlogAndTag;
import com.whpu.blog.dto.BlogQuery;
import com.whpu.blog.dto.SearchBlog;
import com.whpu.blog.pojo.Blog;
import com.whpu.blog.pojo.Type;
import com.whpu.blog.pojo.User;
import com.whpu.blog.service.BlogService;
import com.whpu.blog.service.TagService;
import com.whpu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName BlogController
 * @Description: 关于博客文章操作的控制器
 * @Author jy
 * @Date 2020/4/22
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";
    private static final Integer pageSize = 5;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    private void setTypeAndTage(Model model){
        model.addAttribute("typeList", typeService.getAllTypes());
        model.addAttribute("tagList", tagService.getAllTags());
    }

    /**
     * @MethodName: blogs
     * @Description: 博客文章管理页面，通过pagehelper分页，将查询到的分类列表和分页后的数据传递给blogs页面
     * @Param: [pageNum, model]
     * @Return: java.lang.String
     **/
    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> blogList = blogService.getAllBlogs();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogList);
        List<Type> typeList = typeService.getAllTypes();
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        return LIST;
    }

    /**
     * @MethodName: search
     * @Description: 通过标题，分类，是否推荐来动态查询出博客文章
     * @Param: [pageNum, searchBlog, model]
     * @Return: java.lang.String
     **/
    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         SearchBlog searchBlog, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> blogList = blogService.getBlogBySearch(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogList);
        List<Type> typeList = typeService.getAllTypes();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("typeList", typeList);
        return LIST;
    }

    /**
    * @MethodName: input
    * @Description: TODO
    * @Param: [model]
    * @Return: java.lang.String 
    **/
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTage(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog,HttpSession session,RedirectAttributes redirectAttributes){
        System.out.println(blog);
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        int i = blogService.saveBlog(blog);
        String ids = blog.getTagIds();
        if(!ids.contains(",")){
            blogService.saveBlogAndTag(new BlogAndTag(Integer.parseInt(ids),blog.getId()));
        }else{
            String[] str = ids.split(",");
            for (int j=0;j<str.length;j++){
                blogService.saveBlogAndTag(new BlogAndTag(Integer.parseInt(str[j]),blog.getId()));
            }
        }
        if(i==0){
            redirectAttributes.addFlashAttribute("message","新增失败！(´_ゝ`)");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功！d(d＇∀＇)");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Integer id,Model model){
        setTypeAndTage(model);
        model.addAttribute("blog",blogService.getBlog(id));
        return INPUT;
    }

    @PostMapping("/blogs/{id}")
    public String editPost(@PathVariable Integer id ,Blog blog, RedirectAttributes redirectAttributes){
        int i = blogService.updateBlog(blog);
        if(i==0){
            redirectAttributes.addFlashAttribute("message","修改失败！(´_ゝ`)");
        }else{
            redirectAttributes.addFlashAttribute("message","修改成功！d(d＇∀＇)");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        int i = blogService.deleteBlog(id);
        if(i==0){
            redirectAttributes.addFlashAttribute("message","删除失败！(´_ゝ`)");
        }else{
            redirectAttributes.addFlashAttribute("message","删除成功！d(d＇∀＇)");
        }
        return REDIRECT_LIST;
    }

}
