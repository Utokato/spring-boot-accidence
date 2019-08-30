package cn.llman.deploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 引入spring-boot-devtools后，每次更改内容后
 * 再次 build project，快捷键 Ctrl+F9
 * 就可以实现热部署，不用频繁的开关项目
 *
 * @author
 * @date 2019/1/21
 */
@Controller
public class HelloController {

    @GetMapping("/hi")
    public String hello() {
        return "hello";
    }
}
