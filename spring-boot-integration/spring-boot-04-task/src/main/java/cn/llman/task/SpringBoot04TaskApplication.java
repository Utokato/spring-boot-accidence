package cn.llman.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 异步任务、定时任务、邮件任务
 *
 * {@link EnableAsync} 使用注解开启异步调用的支持
 * {@link EnableScheduling} 使用注解开启定时任务的支持
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpringBoot04TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04TaskApplication.class, args);
    }

}

