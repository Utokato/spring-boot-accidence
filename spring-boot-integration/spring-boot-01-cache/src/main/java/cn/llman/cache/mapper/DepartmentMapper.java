package cn.llman.cache.mapper;

import cn.llman.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author
 * @date 2019/1/16
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDeptById(Integer id);

    @Select("SELECT * FROM department WHERE departmentName=#{departmentName}")
    Department getDeptByName(String departmentName);
}
