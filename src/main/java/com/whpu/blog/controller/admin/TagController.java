package com.whpu.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.pojo.Tag;
import com.whpu.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName TagController
 * @Description: 关于标签操作的控制器
 * @Author jy
 * @Date 2020/4/22
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    private static final Integer pageSize = 5;

    @Autowired
    private TagService tagService;

    /**
     * @MethodName: tags
     * @Description: 展现标签的页面，通过pagehelper分页，将分页后的数据传递给tags页面
     * @Param: [pageNum, model]
     * @Return: java.lang.String
     **/
    @GetMapping("/tags")
    public String tags(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tagList = tagService.getAllTags();
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    /**
     * @MethodName: input
     * @Description: 跳转到新增页面
     * @Param: [model]
     * @Return: java.lang.String
     **/
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * @MethodName: post
     * @Description: 完成新增操作，先查询传递过来的tag有没有重复的，之后校验传来的tag是否为空。前两者没有问题后，再进行新增数据
     * @Param: [tag, bindingResult, redirectAttributes]
     * @Return: java.lang.String
     **/
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName != null){
            bindingResult.rejectValue("name","nameError","您输入的标签名称已存在，不可以重复添加哦( ¬､¬)");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        int i = tagService.saveTag(tag);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功！d(d＇∀＇)");
        }
        return "redirect:/admin/tags";
    }

    /**
     * @MethodName: editInput
     * @Description: 通过id查询到tag，将tag数据传给tags-input页面，在tags-input页面完成修改操作
     * @Param: [id, model]
     * @Return: java.lang.String
     **/
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "/admin/tags-input";
    }

    /**
     * @MethodName: editPost
     * @Description: 修改tag，检验是否重复，检验是否为空，之后完成修改数据
     * @Param: [tag, bindingResult, id, redirectAttributes]
     * @Return: java.lang.String
     **/
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult bindingResult,@PathVariable Integer id,RedirectAttributes redirectAttributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName != null){
            bindingResult.rejectValue("name","nameError","您输入的标签名称已存在，不可以修改成这个哦( ¬､¬)");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        int i = tagService.updateTag(tag);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "修改失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "修改成功！d(d＇∀＇)");
        }
        return "redirect:/admin/tags";
    }

    /**
     * @MethodName: delete
     * @Description: 通过id删除tag
     * @Param: [id, redirectAttributes]
     * @Return: java.lang.String
     **/
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        int i = tagService.deleteTag(id);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "删除失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除成功！d(d＇∀＇)");
        }
        return "redirect:/admin/tags";
    }
}
