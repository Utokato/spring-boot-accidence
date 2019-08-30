package cn.llman.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author
 * @date 2019/1/4
 */
public class DefaultResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        resourceLoader.addProtocolResolver(((location, loader) -> {
            if (location.startsWith("fttpp://")) {
                return new ClassPathResource(location.substring("fttpp://".length()));
            }
            return null;
        }));
        Resource resource = resourceLoader.getResource("fttpp://application.properties");
        InputStream inputStream = resource.getInputStream();
        String string = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(string);
    }
}
