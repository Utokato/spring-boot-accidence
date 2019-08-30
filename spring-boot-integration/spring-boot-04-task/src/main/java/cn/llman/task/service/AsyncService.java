package cn.llman.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019/1/18
 */
@Service
public class AsyncService {


    /**
     * {@link Async} 使用注解声明这个方法支持异步调用
     */
    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
            System.out.println("处理数据中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
