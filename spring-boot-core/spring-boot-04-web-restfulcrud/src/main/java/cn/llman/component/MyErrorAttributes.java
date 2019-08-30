package cn.llman.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author
 * @date 2019/1/7
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * @param webRequest
     * @param includeStackTrace
     * @return Map<String , Object> 这个map就是页面和json能够识别的所有字段
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> returnMap = super.getErrorAttributes(webRequest, includeStackTrace);
        returnMap.put("coder", "lma");
        // getAttribute(),第一个参数是属性的名称，第二个参数是从哪个域对象中获取，0代表request域，1代表session域
        Map<String, Object> ext = (Map) webRequest.getAttribute("ext", 0);
        returnMap.put("ext", ext);
        return returnMap;
    }
}
