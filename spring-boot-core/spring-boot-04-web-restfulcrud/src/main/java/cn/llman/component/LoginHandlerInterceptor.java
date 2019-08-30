package cn.llman.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录校验
 *
 * @author
 * @date 2019/1/3
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行前进行预检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (user != null) {
            return true;
        } else {
            // 未登录，返回登录页面
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
    }
}
