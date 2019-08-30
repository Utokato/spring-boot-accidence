package cn.llman.config;

import cn.llman.component.LoginHandlerInterceptor;
import cn.llman.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * {@link WebMvcConfigurer}
 * {@link WebMvcConfigurerAdapter}
 * 在 JDK8 中接口可以实现默认方法，这使得以前的适配类都失去意义了
 * 所以如上所示，WebMvcConfigurerAdapter作为WebMvcConfigurer的适配器类，已经标注过期了
 * <p>
 * {@link EnableWebMvc} 使用该注解标注我们自定义的配置类后，
 * Spring-boot对于Spring-MVC的自动配置{@link WebMvcAutoConfiguration}就会失效
 * 这就意味着，我们手动全面的接管了Spring-MVC
 *
 * @author
 * @date 2019/1/3
 */
// @EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * <mvc:view-controller path="/home" view-name="success"/>
     * 下面的配置，与我们在配置文件中所写的配置功能一致
     * 即：浏览器发送 /home请求，就会解析到 success.html 页面
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("success");
    }

    /**
     * 最终所有的WebMvcConfigurer组件都会一起在Spring-boot中生效
     * 使用{@link Bean}注解，将该组件加入到容器中
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            // 添加视图控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }

            // 添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 静态资源： *.css,*.js 等 Spring-boot已经做好了静态资源映射
                // --(Springboot2.0有异常) 我们自己的拦截器就不需要额外的处理放行静态资源
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login", "/asserts/**", "/webjars/**");
            }

            // 添加资源处理器
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


}
