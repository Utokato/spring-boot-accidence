package cn.llman.amqp.service;

import cn.llman.amqp.common.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lma
 * @date 2019/08/12
 */
@Component
public class RabbitProducer {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[Producer] send msg:" + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend(Constants.QUEUE_NAME, dateString);
    }
}
