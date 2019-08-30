package cn.llman.config;

import cn.llman.filter.MyFilter;
import cn.llman.listener.MyListener;
import cn.llman.servlet.MyServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2019/1/7
 */
@Configuration
public class MyServerConfig {

    /**
     * 注册三大组件
     * servlet
     * filter
     * listener
     */
    @Bean
    public ServletRegistrationBean<MyServlet> myServlet() {
        return new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> myFilter = new FilterRegistrationBean<>();
        myFilter.setFilter(new MyFilter());
        myFilter.addUrlPatterns("/**");
        return myFilter;
    }

    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener(){
        return new ServletListenerRegistrationBean<>(new MyListener());
    }


    /**
     * 配置嵌入式的servlet容器
     *
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(9000);
        return factory;
    }
}
