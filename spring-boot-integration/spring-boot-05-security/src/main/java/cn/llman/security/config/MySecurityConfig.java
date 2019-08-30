package cn.llman.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author
 * @date 2019/1/19
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * TODO 整理
     * 定制授权的规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制授权的规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能，如果没有登录权限，就会自动跳转到登录页面
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/userLogin");
        // 1. /login 来到登录页
        // 2. 登录失败重定向到/login?error
        // 3. 更多细致的定制规则
        // 4. 默认post形式的 /login 代表处理登录
        // 5. 一旦定制了loginPage，那么loginPage的post请求就是登录

        // 开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");
        // 1. 访问 /logout 表示用户注销，清空session
        // 2. 注销成功，默认会返回 /login?logout 页面
        // 3. logoutSuccessUrl("/")，可以调整注销以后跳转到首页

        // 开启记住我 功能
        http.rememberMe().rememberMeParameter("remember");
        // 登录成功以后，将cookie发送给浏览器，以后访问页面携带该cookie，只要通过检查就可以免登录
        // 点击注销，就会清除该cookie
    }

    /**
     * 定义认证规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("lma").password(encodePassword("idyllo")).roles("VIP1", "VIP2")
                .and()
                .withUser("mal").password(encodePassword("idyllo")).roles("VIP2", "VIP3")
                .and()
                .withUser("marlonn").password(encodePassword("idyllo")).roles("VIP1", "VIP3");

    }

    /**
     * 给IOC中注入一个PasswordEncoder
     * 用于给密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    PasswordEncoder passwordEncoder;

    private String encodePassword(String primaryPassword) {
        return passwordEncoder.encode(primaryPassword);
    }

}
