package cn;

import cn.llman.HelloWorldMainApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 这个类在{@link HelloWorldMainApplication} 启动类的上一级目录
 * 所以没有办法扫描到，所以该类中定义的方法不能被正确映射
 * 也就是说，启动类默认扫描同级及其子目录的类
 * 如果想要扫描到该类，可以在配置类中使用 {@link ComponentScan} 来指定扫描的包
 *
 * @author
 * @date 2019/1/2
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello-world")
    public String hello() {
        return "Hello World!";
    }
}
