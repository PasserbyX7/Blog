package cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cn.domain.Comment;
import cn.service.BlogService;
import cn.service.CommentService;

/**
 * CommentController
 */
@Controller
public class CommentController {

    @GetMapping("/comments/{blogId}")//展示博客评论
    public String comments(@PathVariable Long blogId,Model model){
        model.addAttribute("comments",blogService.getBlog(blogId).getComments());
        return "blog::commentList";
    }

    @PostMapping("/comment")//增
    public String post(Comment comment) {
        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }
    @Value("${comment.avatar}")
    private String avatar;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
}