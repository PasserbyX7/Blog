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

import cn.domain.Type;
import cn.service.BlogService;
import cn.service.TypeService;

/**
 * TypeController
 */
@Controller
public class TypeController {

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 2,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,@PathVariable Long id,Model model) {
        //拿到以blog个数递减的全部type，作为页面的上半部展示
        List<Type>types=typeService.listTopType((typeService.getTotalNum().intValue()));
        //确定被选中的type
        //约定：若前端传来的id值为-1，则默认选中第一个type
        if(id==-1)
            id=types.get(0).getId();
        model.addAttribute("page", blogService.listBlogByType(pageable,typeService.getType(id)));
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",id);
        return "type";
    }
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
}