package cn.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.domain.Blog;
import cn.service.BlogService;

/**
 * BlogsController
 */
@Controller
@RequestMapping("/admin")
public class BlogsController {

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,Blog blog,Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }
    @GetMapping("/blogEdit")
    public String editBlog(){
        return "admin/blogEdit";
    }
    @Autowired
    private BlogService blogService;
}