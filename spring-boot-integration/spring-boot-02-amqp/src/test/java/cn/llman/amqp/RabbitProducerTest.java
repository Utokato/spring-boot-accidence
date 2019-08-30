package cn.llman.amqp;

import cn.llman.amqp.service.RabbitProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author lma
 * @date 2019/08/12
 */
public class RabbitProducerTest extends SpringBoot02AmqpApplicationTests {

    @Autowired
    private RabbitProducer producer;


    @Test
    public void testSendMsg() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            producer.sendMsg();
        }
    }
}
