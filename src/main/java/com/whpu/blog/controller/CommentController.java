package com.whpu.blog.controller;

import com.whpu.blog.pojo.Comment;
import com.whpu.blog.pojo.User;
import com.whpu.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName CommentController
 * @Description: TODO
 * @Author jy
 * @Date 2020/4/25
 **/
@Controller
public class CommentController {

    @Value("${comment.avatar}")
    private String avatar;

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model){
        List<Comment> comments = commentService.getCommentsByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }

}
