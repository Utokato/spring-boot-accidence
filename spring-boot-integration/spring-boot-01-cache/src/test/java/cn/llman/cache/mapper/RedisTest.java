package cn.llman.cache.mapper;

import cn.llman.cache.SpringBoot01CacheApplicationTests;
import cn.llman.cache.bean.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author
 * @date 2019/1/17
 */
public class RedisTest extends SpringBoot01CacheApplicationTests {


    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 操作K-V都是Object
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作K-V都是String
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Employee> empRedisTemplate;

    /**
     * Redis 常用的数据类型
     * -    String 字符串  stringRedisTemplate.opsForValue();
     * -    List 列表     stringRedisTemplate.opsForList();
     * -    Set 集合      stringRedisTemplate.opsForSet();
     * -    Hash 散列     stringRedisTemplate.opsForHash();
     * -    ZSet 有序集合   stringRedisTemplate.opsForZSet();
     */
    @Test
    public void testOne() {
        // 给Redis中保存一个数据
        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }


    @Test
    public void testTwo() {
        stringRedisTemplate.opsForList().leftPushAll("myList", "1", "2", "3", "4", "5");
        String leftPop = stringRedisTemplate.opsForList().leftPop("myList");
        String rightPop = stringRedisTemplate.opsForList().rightPop("myList");
        System.out.println("leftPop is: " + leftPop + ", rightPop is: " + rightPop);
    }

    /**
     * 测试保存对象
     */
    @Test
    public void testThree() {
        Employee emp = employeeMapper.getEmpById(1);
        // 默认保存对象，可以使用JDK的序列化机制
        redisTemplate.opsForValue().set("emp-01", emp);
        // 将数据以JSON的格式保存到Redis中
        // 1. 手动将对象通过JSON工具转换为JSON对象
        // 2. redisTemplate 有默认的序列化规则,可以更改序列化的规则来使用JSON
        empRedisTemplate.opsForValue().set("employee-01", emp);
    }
}
