package cn.llman.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 使用JPA注解配置映射关系
 * - {@link Entity} 声明这使用一个实体类，用于和数据库中的表映射
 * - {@link Table} 指定和数据库中的哪一张表对应，默认使用类名的小写作为表名，如：user
 * - {@link Id} 声明这是主键
 * - {@link GeneratedValue} 声明主键的生成策略
 * - {@link Column} 对应于数据库表中的一列，可以指定列名，默认属性名就是列名
 * <p>
 * - {@link Data} lombok的注解，帮助生成getter/setter和构造方法
 *
 * @author
 * @date 2019/1/13
 */
@Entity
@Table(name = "tbl_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column
    private String email;
}
