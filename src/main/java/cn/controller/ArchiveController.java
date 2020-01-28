package cn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.service.BlogService;

/**
 * ArchiveController
 */
@Controller
public class ArchiveController {

    @GetMapping("/archive")
    public String archives(Model model) {
        model.addAttribute("archiveList",blogService.listBlogByArchive());
        model.addAttribute("blogNum",blogService.getTotalNum());
        return "archive";
    }
    @Autowired
    BlogService blogService;
}