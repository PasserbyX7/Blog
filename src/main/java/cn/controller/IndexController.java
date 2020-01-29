package cn.controller;

import org.springframework.data.domain.Sort;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.service.BlogService;
import cn.service.TagService;
import cn.service.TypeService;

/**
 * IndexController
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",blogService.listPublishedBlog(pageable));
        model.addAttribute("types", typeService.listTopType(typeNum));
        model.addAttribute("tags", tagService.listTopTag(tagNum));
        model.addAttribute("recommendBlogs", blogService.listTopBlog(recommendBlogNum));
        return "index";
    }
    @Autowired
    private BlogService blogService;
    @Autowired  
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Value("${index.typeNum}")
    private Integer typeNum;
    @Value("${index.tagNum}")
    private Integer tagNum;
    @Value("${index.recommendBlogNum}")
    private Integer recommendBlogNum;
}