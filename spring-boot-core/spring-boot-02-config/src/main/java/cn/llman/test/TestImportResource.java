package cn.llman.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Arrays;

/**
 * @author
 * @date 2019/1/8
 */
public class TestImportResource {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        applicationContext.close();
    }

}
