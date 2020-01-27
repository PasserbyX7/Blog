package cn.controller;

import org.springframework.data.domain.Sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.domain.Blog;
import cn.domain.Tag;
import cn.service.BlogService;
import cn.service.TagService;

/**
 * TagShowController
 */
@Controller
public class TagShowController {

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 2,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,@PathVariable Long id,Model model) {
        List<Tag>tags=tagService.listTopTag((tagService.getTotalNum().intValue()));
        //约定：若前端传来的id值为-1，则默认选中第一个tag
        if(id==-1)
            id=tags.get(0).getId();
        model.addAttribute("page", blogService.listBlog(pageable,tagService.getTag(id)));
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
}