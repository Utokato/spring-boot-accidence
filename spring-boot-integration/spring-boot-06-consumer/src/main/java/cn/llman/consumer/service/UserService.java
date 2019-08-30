package cn.llman.consumer.service;

import cn.llman.provider.service.TicketService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019/1/21
 */
@Service
public class UserService {

    @Reference
    TicketService ticketService;

    void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("Gotten a ticket: " + ticket);
    }

}
