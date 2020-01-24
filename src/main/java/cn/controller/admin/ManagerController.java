package cn.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ManagerController
 */
@Controller
@RequestMapping("/admin")
public class ManagerController {

    @GetMapping("/manager")
    public String manager(){
        return "admin/manager";
    }
}