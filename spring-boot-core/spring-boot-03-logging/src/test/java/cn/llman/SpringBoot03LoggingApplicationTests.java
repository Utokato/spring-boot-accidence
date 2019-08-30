package cn.llman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot03LoggingApplicationTests {

    // 获取记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
        // 日志的级别
        // 由低到高： trace<debug<info<warn<error
        // 可以调整输出的日志级别：只打印该级别和高级别信息
        logger.trace("It is a trace ");
        logger.debug("It is a debug ");
        // Spring默认使用的info级别的日志记录信息
        // 可以在配置文件中配置日志输出的级别，没有配置的话，默认使用Spring设置的级别：root级别
        logger.info("It is a info ");
        logger.warn("It is a warn ");
        logger.error("It is a error ");
    }

}

