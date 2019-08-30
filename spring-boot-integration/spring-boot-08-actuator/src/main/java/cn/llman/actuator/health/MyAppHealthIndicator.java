package cn.llman.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2019/1/21
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 自定义检查方法

        // up 表示健康状态
        return Health.up().withDetail("Status", "App running normally").build();
    }
}
