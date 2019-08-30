package cn.llman.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019/1/18
 */
@Service
public class ScheduledService {

    /**
     * second
     * minute
     * hour
     * day of month
     * month
     * day of week
     * <p>
     * 关于cron表达式的具体写法
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello() {
        System.out.println("Hello...");
    }
}
