package cn.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.domain.User;
import cn.service.UserService;

/**
 * LoginController
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @GetMapping
    public String toLoginPage(){
        return "admin/login";
    }
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }
    @PostMapping("/login")
    public String login(String username,String password,HttpSession session,RedirectAttributes attributes,Model model){
        User user=userService.checkUser(username, password);
        if(user!=null){
            session.setAttribute("user", user);
            user.setPassword(null);//把密码传到前台很不安全
            return "admin/index";
        }
        else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
    @Autowired
    private UserService userService;
}