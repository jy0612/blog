package com.whpu.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.blog.pojo.Type;
import com.whpu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName TypeShowController
 * @Description: 关于分类操作的控制器
 * @Author jy
 * @Date 2020/4/21
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    private static final Integer pageSize = 5;

    @Autowired
    private TypeService typeService;

    /**
    * @MethodName: types
    * @Description: 展现分类的页面，通过pagehelper分页，将分页后的数据传递给types页面
    * @Param: [pageNum, model]
    * @Return: java.lang.String
    **/
    @GetMapping("/types")
    public String types(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> typeList = typeService.getAllTypes();
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /**
    * @MethodName: input
    * @Description: 跳转到新增页面
    * @Param: [model]
    * @Return: java.lang.String
    **/
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
    * @MethodName: post
    * @Description: 完成新增操作，先查询传递过来的type有没有重复的，之后校验传来的type是否为空。前两者没有问题后，再进行新增数据
    * @Param: [type, bindingResult, redirectAttributes]
    * @Return: java.lang.String
    **/
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            bindingResult.rejectValue("name","nameError","您输入的分类名称已存在，不可以重复添加哦( ¬､¬)");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        int i = typeService.saveType(type);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功！d(d＇∀＇)");
        }
        return "redirect:/admin/types";
    }

    /**
    * @MethodName: editInput
    * @Description: 通过id查询到type，将type数据传给types-input页面，在types-input页面完成修改操作
    * @Param: [id, model]
    * @Return: java.lang.String
    **/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Integer id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "/admin/types-input";
    }

    /**
    * @MethodName: editPost
    * @Description: 修改type，检验是否重复，检验是否为空，之后完成修改数据
    * @Param: [type, bindingResult, id, redirectAttributes]
    * @Return: java.lang.String
    **/
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult bindingResult,@PathVariable Integer id,RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            bindingResult.rejectValue("name","nameError","您输入的分类名称已存在，不可以修改成这个哦( ¬､¬)");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        int i = typeService.updateType(type);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "修改失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "修改成功！d(d＇∀＇)");
        }
        return "redirect:/admin/types";
    }

    /**
    * @MethodName: delete
    * @Description: 通过id删除type
    * @Param: [id, redirectAttributes]
    * @Return: java.lang.String
    **/
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        int i = typeService.deleteType(id);
        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "删除失败！(´_ゝ`)");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除成功！d(d＇∀＇)");
        }
        return "redirect:/admin/types";
    }
}
