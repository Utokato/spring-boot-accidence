package cn.llman.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 将服务注册到服务中心(zookeeper)
 * -    1. 引入dubbo和zkclient等相关的依赖
 * -    2. 配置dubbo的扫描包和注册中心的地址
 * -    3. 使用@Service发布服务
 */
@SpringBootApplication
public class SpringBoot06ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06ProviderApplication.class, args);
    }

}

