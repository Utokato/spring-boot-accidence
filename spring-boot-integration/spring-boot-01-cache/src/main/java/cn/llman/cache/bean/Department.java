package cn.llman.cache.bean;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 4138897674238130320L;
    private Integer id;
    private String departmentName;

    public Department() {
        super();
    }

    public Department(Integer id, String departmentName) {
        super();
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", departmentName=" + departmentName + "]";
    }

}
