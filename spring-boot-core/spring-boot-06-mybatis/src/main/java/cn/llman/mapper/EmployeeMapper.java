package cn.llman.mapper;

import cn.llman.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author
 * @date 2019/1/12
 */
@Mapper
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    void insertEmp(Employee employee);
}
