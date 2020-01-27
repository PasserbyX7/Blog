package cn.controller.admin;

import java.util.Arrays;
import java.util.stream.Collectors;

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
import cn.domain.Tag;
import cn.domain.Type;
import cn.domain.User;
import cn.service.BlogService;
import cn.service.TagService;
import cn.service.TypeService;

/**
 * BlogsController
 */
@Controller
@RequestMapping("/admin")
public class BlogsController {

    @GetMapping("/blogEdit")//点击添加按钮，跳转至Edit页面
    public String toAddBlog(Model model){
        init(model, null);
        return EDIT;
    }
    @GetMapping("/blogEdit/{id}")//点击编辑按钮，跳转至Edit页面
    public String toEditBlog(@PathVariable Long id,Model model){
        init(model, blogService.getBlog(id));
        return EDIT;
    }
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,Blog blog,Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        model.addAttribute("types",typeService.listType());
        return LIST;
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"}, direction = Sort.Direction.DESC) 
    Pageable pageable,Blog blog,Model model){
        Type type=new Type();
        // type.setId(typeId);
        // blog.setType(type);
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "admin/blogs::blogList";
    }
    //增、改二合一
    @PostMapping("/blog")
    public String addBlog(Blog blog,String tagIds,HttpSession session){
        blog.setType(typeService.getType(blog.getType().getId()));
        //通过Stream将形如"13,23"的字符串转为List<Long>ids，再通过ids查询出Tags并setter
        if(tagIds!=null&&!tagIds.isEmpty())
            blog.setTags(tagService.listTag(Arrays.asList(tagIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList())));
        blog.setUser((User)session.getAttribute("user"));
        blogService.saveBlog(blog);
        return REDIRECT_LIST;
    }
    //删
    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return REDIRECT_LIST;
    }
    /**
     * set tag、type and blog
     */
    private void init(Model model,Blog blog){
        if(blog==null)
            blog=new Blog();
        //为session存入tag的字符串版本
        if(blog.getTags()!=null){
            String tagIds=String.join(",", blog.getTags().stream().map(Tag::getId).map(String::valueOf).collect(Collectors.toList()));
            model.addAttribute("tagIds",tagIds);
        }
        model.addAttribute("blog",blog);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    private static final String LIST="admin/blogs";
    private static final String EDIT="admin/blogEdit";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";
}