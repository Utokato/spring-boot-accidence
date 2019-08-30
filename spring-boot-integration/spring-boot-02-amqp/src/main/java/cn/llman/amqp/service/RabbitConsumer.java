package cn.llman.amqp.service;

import cn.llman.amqp.common.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lma
 * @date 2019/08/12
 */
@Component
@RabbitListener(queues = Constants.QUEUE_NAME)
public class RabbitConsumer {

    private final AmqpTemplate template;

    @Autowired
    public RabbitConsumer(AmqpTemplate template) {
        this.template = template;
    }

    @RabbitHandler
    public void receiveMsg(String msg) {
        System.out.println("[Consumer] receive message:" + msg);
    }
}
