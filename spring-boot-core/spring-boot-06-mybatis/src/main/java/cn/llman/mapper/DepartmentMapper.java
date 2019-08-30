package cn.llman.mapper;

import cn.llman.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * 使用 {@link Mapper} 指定该类是操作数据库的mapper
 *
 * @author
 * @date 2019/1/12
 */
// @Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDeptById(Integer id);

    @Delete("DELETE FROM department WHERE id=#{id}")
    int deleteDeptById();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department(departmentName) VALUES(#{departmentName})")
    int insertDept(Department department);

    @Update("UPDATE department set departmentName=#{departmentName} WHERE id=#{id}")
    int updateDept(Department department);

}
