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
 * AdminTypeController
 */
@Controller
@RequestMapping("/admin")
public class AdminTypeController {

    @GetMapping("/type")//来到展示页面，分页显示所有type
    public String types(@PageableDefault(size=10,sort = {"id"},direction=Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return LIST;
    }
    @GetMapping("/typeEdit")//点击添加按钮，跳转至Edit页面
    public String toTypeAddPage(Model model){
        model.addAttribute("type",new Type());
        return EDIT;
    }
    @GetMapping("/typeEdit/{id}")//点击编辑按钮，跳转至Edit页面
    public String toTypeEditPage(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return EDIT;
    }
    //增
    @PostMapping("/types")
    public String addType(@Valid Type type,BindingResult bindingResult ,RedirectAttributes attributes){
        if(typeService.getTypeByName(type.getName())!=null)
            bindingResult.rejectValue("name", "nameError","该分类已存在");
        if(bindingResult.hasErrors())
            return "admin/typeEdit";
        typeService.saveType(type);
        return REDIRECT_LIST;
    }
    //删
    @DeleteMapping("/types/{id}")
    public String deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return REDIRECT_LIST;
    }
    //改
    @PutMapping("/types")
    public String updateType(Type type){
        typeService.saveType(type);
        return REDIRECT_LIST;
    }
    @Autowired
    private TypeService typeService;
    private static final String LIST="admin/type";
    private static final String EDIT="admin/typeEdit";
    private static final String REDIRECT_LIST="redirect:/admin/type";
}