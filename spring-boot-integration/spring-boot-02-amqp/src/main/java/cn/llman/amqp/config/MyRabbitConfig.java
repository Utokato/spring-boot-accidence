package cn.llman.amqp.config;

import cn.llman.amqp.common.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2019/1/18
 */
@Configuration
public class MyRabbitConfig {

    @Bean
    public Queue createQueue() {
        return new Queue(Constants.QUEUE_NAME);
    }

    @Bean
    public Queue createLmaQueue() {
        return new Queue("lma");
    }

    @Bean
    public Queue createLmaEmpQueue() {
        return new Queue("lma.emps");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
