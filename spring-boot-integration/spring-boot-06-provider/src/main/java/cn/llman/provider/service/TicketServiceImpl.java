package cn.llman.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * {@link Service} 使用Dubbo的@Service注解发布服务
 *
 * @author
 * @date 2019/1/21
 */
@Component
@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "A ticket";
    }
}
