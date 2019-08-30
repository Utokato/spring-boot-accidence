package cn.llman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link RestController} 是一个组合注解，包含了{@link Controller}和{@link ResponseBody}
 * 这个类的所有方法返回的数据直接写给浏览器，如果是对象转为json数据
 *
 * @author
 * @date 2019/1/2
 */
/*@Controller
@ResponseBody*/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World Quick!";
    }

    // REST API的方式
}
