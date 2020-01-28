package cn.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.domain.Tag;
import cn.service.TagService;

/**
 * TagController
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @GetMapping("/tags")//来到展示页面，分页显示所有tag
    public String tags(@PageableDefault(size=10,sort = {"id"},direction=Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return LIST;
    }
    @GetMapping("/tagEdit")//点击添加按钮，跳转至Edit页面
    public String toTagAddPage(Model model){
        model.addAttribute("tag",new Tag());
        return EDIT;
    }
    @GetMapping("/tag/{id}")//点击编辑按钮，跳转至Edit页面
    public String toTagEditPage(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return EDIT;
    }
    //增
    @PostMapping("/tag")
    public String addTag(@Valid Tag tag,BindingResult bindingResult ,RedirectAttributes attributes){
        if(tagService.getTagByName(tag.getName())!=null)
            bindingResult.rejectValue("name", "nameError","该标签已存在");
        if(bindingResult.hasErrors())
            return EDIT;
        tagService.saveTag(tag);
        return REDIRECT_LIST;
    }
    //删
    @DeleteMapping("/tag/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return REDIRECT_LIST;
    }
    //改
    @PutMapping("/tag")
    public String updateTag(Tag tag){
        tagService.saveTag(tag);
        return REDIRECT_LIST;
    }
    @Autowired
    private TagService tagService;
    private static final String LIST="admin/tag";
    private static final String EDIT="admin/tagEdit";
    private static final String REDIRECT_LIST="redirect:/admin/tag";
}