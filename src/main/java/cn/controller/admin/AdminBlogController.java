package cn.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.domain.Blog;
import cn.domain.User;
import cn.service.BlogService;
import cn.service.TagService;
import cn.service.TypeService;

/**
 * AdminBlogController
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    @GetMapping("/blogEdit") // 点击添加按钮，跳转至Edit页面
    public String toAddBlog(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return EDIT;
    }

    @GetMapping("/blogEdit/{id}") // 点击编辑按钮，跳转至Edit页面
    public String toEditBlog(@PathVariable Long id, Model model) {
        Blog blog=blogService.getBlog(id);
        // 为session存入tag的字符串版本
        if (blog.getTags() != null)
            model.addAttribute("tagIds", tagService.TagListToString(blog.getTags()));
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return EDIT;
    }

    @GetMapping("/blog") // blog列表展示页面
    public String blogs(
            @PageableDefault(size = 7, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            Blog blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.listType());
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(
            @PageableDefault(size = 7, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            Blog blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST + "::blogList";
    }

    // 增、改blog
    @PostMapping("/blogs")
    public String addBlog(Blog blog, String tagIds, HttpSession session) {
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog的tags
        if (tagIds != null && !tagIds.isEmpty())
            blog.setTags(tagService.StringToTagList(tagIds));
        //设置blog的user
        blog.setUser((User) session.getAttribute("user"));
        //保存blog
        blogService.saveBlog(blog);
        return REDIRECT_LIST;
    }

    // 删
    @DeleteMapping("/blogs/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return REDIRECT_LIST;
    }

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    private static final String LIST = "admin/blog";
    private static final String EDIT = "admin/blogEdit";
    private static final String REDIRECT_LIST = "redirect:/admin/blog";
}