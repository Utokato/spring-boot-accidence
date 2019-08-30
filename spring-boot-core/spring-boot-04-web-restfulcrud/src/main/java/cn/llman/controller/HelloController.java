package cn.llman.controller;

import cn.llman.config.MyMvcConfig;
import cn.llman.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author
 * @date 2019/1/2
 */
@Controller
public class HelloController {

    /**
     * 可以在配置类中，设置视图控制器
     * {@link MyMvcConfig#webMvcConfigurer()}
     *
     * @return
     */
    // @GetMapping({"/", "/login.html"})
    public String index() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>hello,world</h1>");
        map.put("users", Arrays.asList("Alen", "Bob", "Calth"));
        return "success";
    }

    @ResponseBody
    @GetMapping("/helloException")
    public String helloException(@RequestParam("user") String user) {
        if (user.equals("not")) {
            throw new UserNotExistException();
        }
        return "Hello, world!";
    }

}
