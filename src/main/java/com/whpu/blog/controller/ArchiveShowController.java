package com.whpu.blog.controller;

import com.whpu.blog.dto.AchieveBlog;
import com.whpu.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArchiveShowController
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/26
 **/
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.getAchievesByYear());
        model.addAttribute("count",blogService.getBlogsCount());
        return "archives";
    }

}
