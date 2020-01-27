package cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.domain.Blog;
import cn.service.BlogService;
import cn.util.MarkdownUtils;

/**
 * BlogShowController
 */
@Controller
public class BlogShowController {

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        Blog blog=blogService.getBlog(id);
        blog.setContent(MarkdownUtils.markdownToHTMLExtensions(blog.getContent()));
        model.addAttribute("blog",blog);
        return "blog";
    }
    @Autowired
    BlogService blogService;
}