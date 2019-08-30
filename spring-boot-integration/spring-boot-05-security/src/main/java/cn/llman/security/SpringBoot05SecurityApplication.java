package cn.llman.security;

import cn.llman.security.config.MySecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 1. 引入Spring-boot的认证授权模块
 * 2. 编写SpringSecurity的配置文件
 * -    自定义{@link MySecurityConfig}类，继承自{@link WebSecurityConfigurerAdapter}
 * 3. 控制请求的访问权限
 */
@SpringBootApplication
public class SpringBoot05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot05SecurityApplication.class, args);
    }

}

