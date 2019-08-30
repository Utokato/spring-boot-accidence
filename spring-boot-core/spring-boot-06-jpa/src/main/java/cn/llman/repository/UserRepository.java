package cn.llman.repository;

import cn.llman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承 {@link JpaRepository} 来完成对数据库的操作
 * 泛型的第一个值：数据库表映射过来的实体类
 * 泛型的第二个值：实体类中主键的数据类型
 *
 * @author
 * @date 2019/1/13
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
