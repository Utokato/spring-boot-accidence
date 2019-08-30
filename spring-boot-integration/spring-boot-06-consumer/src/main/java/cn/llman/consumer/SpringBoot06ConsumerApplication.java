package cn.llman.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 从服务中心中获取服务
 * -    1. 引入相关依赖
 * -    2. 配置dubbo注册中心地址
 * -    3. 引用服务
 */
@SpringBootApplication
public class SpringBoot06ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06ConsumerApplication.class, args);
    }

}

