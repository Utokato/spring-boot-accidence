package cn.llman.amqp;

import cn.llman.amqp.bean.Book;
import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

/**
 * @author
 * @date 2019/1/18
 */
public class TestRabbitMQ extends SpringBoot02AmqpApplicationTests {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 测试单播模式(点对点) -> 发送消息
     * {@link Message}
     * 需要自己构造一个 Message 对象，定义消息头和消息体
     */
    @Test
    public void testDirectSend() {
        // 需要我们自己构造一个message对象，可以自定义消息头和消息体
        // rabbitTemplate.send(exchange,routerKey,message);

        // 将转入的object对象，自动转为message对象，保存发送给rabbitMQ
        // object默认被当做消息体
        // rabbitTemplate.convertAndSend(exchange,routerKey,object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一个消息");
        map.put("data", Arrays.asList("HelloWorld", 123, true));
        map.put("book", new Book("A tale of two cities", "Dickens"));
        // 对象被默认序列化后发送出去，使用JDK的序列化机制
        rabbitTemplate.convertAndSend("lma", map);
    }

    /**
     * 测试单播模式(点对点) -> 接收消息
     */
    @Test
    public void TestDirectReceive() {
        Object obj = rabbitTemplate.receiveAndConvert("lma");
        System.out.println(Objects.requireNonNull(obj).getClass());
        System.out.println(obj);
    }

    /**
     * 测试广播模式 -> 发送消息
     */
    @Test
    public void TestFanoutSend() {
        rabbitTemplate.convertAndSend("exchange.fanout", "",
                new Book("Der Zauberberg", "Thomas Mann"));
    }

    @Test
    public void testCreateExchange() {
        amqpAdmin.declareExchange(new DirectExchange("amqp.admin.exchange"));
        System.out.println("Created exchange by amqp admin.");
    }

    @Test
    public void testCreateQueue() {
        amqpAdmin.declareQueue(new Queue("amqp.admin", true));
        System.out.println("Created queue by amqp admin.");
    }

    @Test
    public void testCreateBinding() {
        amqpAdmin.declareBinding(new Binding("amqp.admin.queue",
                Binding.DestinationType.QUEUE,
                "amqp.admin.exchange",
                "amqp.admin",
                null));
        System.out.println("Create binding by amqp admin.");
    }

}
