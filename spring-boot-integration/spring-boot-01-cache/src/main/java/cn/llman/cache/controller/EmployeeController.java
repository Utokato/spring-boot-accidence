package cn.llman.cache.controller;

import cn.llman.cache.bean.Employee;
import cn.llman.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2019/1/16
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeService.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee) {
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/emp/del/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id);
        return "SUCCESS";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }

}
