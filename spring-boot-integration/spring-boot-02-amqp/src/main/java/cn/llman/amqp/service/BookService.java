package cn.llman.amqp.service;

import cn.llman.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019/1/18
 */
// @Service
public class BookService {

    @RabbitListener(queues = {"lma"})
    public void receive(Book book) {
        System.out.println("Get msg from MQ, and msg is: " + book);
    }

    @RabbitListener(queues = {"lma.emps"})
    public void receiveMessage(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
