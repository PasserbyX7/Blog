package cn.controller;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.service.BlogService;

/**
 * SearchController
 */
@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(@PageableDefault(size = 2,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,Model model,String query) {
        model.addAttribute("page",blogService.listBlog(pageable, query));
        model.addAttribute("query", query);
        return "search";
    }
    @Autowired  
    private BlogService blogService;
}