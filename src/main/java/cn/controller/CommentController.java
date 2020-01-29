package cn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cn.domain.Comment;
import cn.domain.User;
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
    public String addComment(Comment comment,HttpSession session) {
        //如果当前用户是管理员，则设置管理员专属头像及权限
        User user=(User)session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else
            comment.setAvatar(avatar);
        //保存comment
        commentService.saveComment(comment);
        return "redirect:/comment/"+comment.getBlog().getId();//刷新comment且重定向到blog页面
    }

    @DeleteMapping("/comments/{id}")//删
    public String deleteComment(@PathVariable Long id) {
        Long BlogId=commentService.getComment(id).getBlog().getId();
        commentService.deleteComment(id);
        return "redirect:/blog/"+BlogId;
    }

    @Value("${comment.avatar}")
    private String avatar;
    @Autowired
    private CommentService commentService;
}