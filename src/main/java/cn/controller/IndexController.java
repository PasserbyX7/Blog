package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cn.exception.NotFoundException;

/**
 * IndexController
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        throw new NotFoundException("博客不存在");
    }
}