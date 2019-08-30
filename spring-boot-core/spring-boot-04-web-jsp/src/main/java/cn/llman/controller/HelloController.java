package cn.llman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author
 * @date 2019/1/7
 */
@Controller
public class HelloController {

    @GetMapping("/goSuccess")
    public String goSuccess(Model model){
        model.addAttribute("msg","Hello, I come from another page!");
        return "success";
    }
}
