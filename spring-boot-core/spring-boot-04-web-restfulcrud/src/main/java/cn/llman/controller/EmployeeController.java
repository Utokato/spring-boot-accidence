package cn.llman.controller;

import cn.llman.dao.DepartmentDao;
import cn.llman.dao.EmployeeDao;
import cn.llman.entities.Department;
import cn.llman.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author
 * @date 2019/1/6
 */
@Controller
public class EmployeeController {


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有的员工，返回列表页面
     *
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中
        model.addAttribute("emps", employees);

        // thymeleaf 默认会拼接路径
        // classpath:/templates/emp/list.html
        return "emp/list";
    }


    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面，查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 员工添加
     * SpringMVC 自动将请求参数和入参对象的属性进行一一绑定
     * 要求：请求参数的名字和JavaBean入参对象里面的属性名是一致的
     *
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("待保存的员工信息：" + employee);
        // 保存员工信息
        employeeDao.save(employee);
        // 返回到员工列表页面
        // redirect：表示重定向到一个地址   / 代表当前的项目路径
        // forward：表示转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 来到修改页面，查出当前员工，在页面回显
     *
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        // 查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 回到修改页面，和添加页面共用
        return "emp/add";
    }

    /**
     * 修改员工信息，需要提交员工id
     *
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println("修改的员工数据：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
