package cn.llman.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

/**
 * 自动配置
 * 1. 自动配置类 {@link RabbitAutoConfiguration}
 * 2. 其中自动配置了ConnectionFactory -> {@link CachingConnectionFactory}
 * 3. 在连接工厂中需要RabbitMQ的配置文件，这些配置文件都绑定在 {@link RabbitProperties} 类中
 * 4. 同样自动配置了RabbitTemplate，使用{@link RabbitTemplate} 发送和接受信息
 * 5. {@link AmqpAdmin} 是RabbitMQ系统管理功能的组件：创建队列，交换器等
 * -        用于创建和删除Queue、Exchange 等
 * 6. 使用 @EnableRabbit + @RabbitListener 来监听消息队列中的消息
 * <p>
 * 使用{@link EnableRabbit} 注解开启基于注解的RabbitMQ模式
 */
@SpringBootApplication
@EnableRabbit
public class SpringBoot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02AmqpApplication.class, args);
    }

}

