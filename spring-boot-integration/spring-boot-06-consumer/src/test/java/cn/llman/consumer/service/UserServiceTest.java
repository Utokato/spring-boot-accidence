package cn.llman.consumer.service;

import cn.llman.consumer.SpringBoot06ConsumerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest extends SpringBoot06ConsumerApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void hello() {
        userService.hello();
    }
}