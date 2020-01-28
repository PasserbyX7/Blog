package cn.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cn.domain.Comment;
import cn.domain.User;
import cn.service.BlogService;
import cn.service.CommentService;

/**
 * CommentController
 */
@Controller
public class CommentController {

    @GetMapping("/comment/{blogId}")//展示博客评论
    public String comments(@PathVariable Long blogId,Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog::commentList";
    }

    @PostMapping("/comments")//增
    public String post(Comment comment,HttpSession session) {
        User user=(User)session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        commentService.saveComment(comment);
        return "redirect:/comment/"+blogId;
    }
    @Value("${comment.avatar}")
    private String avatar;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
}