package cn.llman.cache.mapper;

import cn.llman.cache.SpringBoot01CacheApplicationTests;
import cn.llman.cache.bean.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class EmployeeMapperTest extends SpringBoot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void getEmpById() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

    @Test
    public void updateEmp() {
    }

    @Test
    public void deleteEmpById() {
    }

    @Test
    public void insertEmp() {
    }
}
