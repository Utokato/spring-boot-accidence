package cn.llman.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author
 * @date 2019/1/16
 */
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator myKeyGenerator() {
        return (target, method, params) -> method.getName() + Arrays.asList(params).toString();
    }
}
