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

import cn.domain.Type;
import cn.service.TypeService;

/**
 * TypesController
 */
@Controller
@RequestMapping("/admin")
public class TypesController {

    @GetMapping("/types")
    public String types(@PageableDefault(size=10,sort = {"id"},direction=Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }
    @GetMapping("/typeEdit")//点击添加按钮，跳转至Edit页面
    public String toTypeAddPage(Model model){
        model.addAttribute("type",new Type());
        return "admin/typeEdit";
    }
    @GetMapping("/type/{id}")//点击编辑按钮，跳转至Edit页面
    public String toTypeEditPage(@PathVariable("id")Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/typeEdit";
    }
    //增
    @PostMapping("/type")
    public String addType(@Valid Type type,BindingResult bindingResult ,RedirectAttributes attributes){
        if(typeService.getTypeByName(type.getName())!=null)
            bindingResult.rejectValue("name", "nameError","该分类已存在");
        if(bindingResult.hasErrors())
            return "admin/typeEdit";
        typeService.saveType(type);
        return "redirect:/admin/types";
    }
    //删
    @DeleteMapping("/type/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
    //改
    @PutMapping("/type")
    public String updateType(Type type){
        typeService.updateType(type);
        return "redirect:/admin/types";
    }
    @Autowired
    private TypeService typeService;
}