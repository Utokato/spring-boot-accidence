package cn.llman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2019/1/12
 */
@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String, Object> map() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM department");
        return maps.get(0);
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
