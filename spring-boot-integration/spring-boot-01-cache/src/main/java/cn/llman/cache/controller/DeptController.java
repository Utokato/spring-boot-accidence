package cn.llman.cache.controller;

import cn.llman.cache.bean.Department;
import cn.llman.cache.service.DeptService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @date 2019/1/17
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }

    @GetMapping("/dept/name/{departmentName}")
    public Department getDeptByName(@PathVariable("departmentName") String departmentName) {
        return deptService.getDeptByName(departmentName);
    }

    @GetMapping("/getCacheManage")
    public List<String> getCacheManage() {
        String[] names = applicationContext.getBeanNamesForType(CacheManager.class);
        return Arrays.asList(names);
    }
}
