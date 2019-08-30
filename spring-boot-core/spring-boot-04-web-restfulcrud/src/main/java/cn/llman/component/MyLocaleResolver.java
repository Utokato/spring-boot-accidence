package cn.llman.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息
 *
 * @author
 * @date 2019/1/3
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 解析区域信息
     * <p>
     * 这里的逻辑是：如果请求的连接中带有语言信息，就根据该语言信息去解析Locale
     * -           如果没有就使用默认的语言信息：{@link Locale#getDefault()}
     * <p>
     * 如果请求连接上不带有语言信息，也可以根据request中的请求头信息中后去语言信息：
     * -     如使用Spring的{@link AcceptHeaderLocaleResolver}来解析Locale
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        // AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        // Locale locale = localeResolver.resolveLocale(request);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(l)) {
            String[] strings = l.split("_");
            locale = new Locale(strings[0], strings[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
