package cn.llman.cache.service;

import cn.llman.cache.bean.Department;
import cn.llman.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019/1/17
 */
@Service
public class DeptService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 使用注解进行缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"dept"})
    public Department getDeptById(Integer id) {
        System.out.println("[部门查询] id: " + id);
        return departmentMapper.getDeptById(id);
    }

    /**
     * 也可以使用编码的方式进行手动缓存
     */
    public Department getDeptByName(String departmentName) {
        System.out.println("[部门查询] name: " + departmentName);

        Department department = departmentMapper.getDeptByName(departmentName);
        // 对查询到的部门进行手动缓存
        Cache cache = redisCacheManager.getCache("dept");
        cache.put("dept-" + departmentName, department);

        return department;
    }

}
