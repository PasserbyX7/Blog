package cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.domain.Blog;
import cn.service.BlogService;

/**
 * BlogController
 */
@Controller
public class BlogController {

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        Blog blog=blogService.getBlog(id);
        model.addAttribute("blog",blogService.BlogContentToHtml(blog));
        return "blog";
    }

    @Autowired
    BlogService blogService;
}