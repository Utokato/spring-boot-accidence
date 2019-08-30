package cn.llman.test;

import cn.llman.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * {@link ImportResource} 导入Spring的配置文件，让配置文件里面的内容生效
 * Springboot 原本没有Spring的配置文件，我们可以自己手动添加编写这些配置文件
 * 但是springboot应用不能自动识别，想要Spring的配置文件加载进来，生效，
 * 就需要@ImportResource来导入这些配置文件，一般将@ImportResource标注在一个配置类上
 * <p>
 * 如：我们在beans.xml这个spring配置文件中利用<bean></bean>标签注册一个bean
 * 使用@ImportResource("classpath:beans.xml")将其导入，
 * 然后在测试类中，我们能看到IOC容器中就存在该bean了
 *
 * @author
 * @date 2019/1/8
 */
@Configuration
@ImportResource("classpath:beans.xml")
public class MyConfig {
    @Bean
    public HelloController helloController2() {
        return new HelloController();
    }
}
