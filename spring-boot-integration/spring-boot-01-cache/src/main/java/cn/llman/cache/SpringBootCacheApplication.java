package cn.llman.cache;

import cn.llman.cache.service.EmployeeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * 一、搭建基本环境
 * 1. 导入数据库文件，创建Department、Employee表
 * 2. 创建JavaBean 封装对应的数据
 * 3. 整合mybatis操作数据库
 * -    a. 配置数据源信息
 * -    b. 使用注解版mybatis
 * -        1) 使用@MapperScan指定需要扫描的mapper接口所在包
 * -        2)
 * 二、快速体验缓存
 * 步骤：
 * 1. 开启基于注解的缓存
 * 2. 标注注解缓存
 * -    @Cacheable
 * -    @CacheEvict
 * -    @CachePut
 * <p>
 * 默认使用：ConcurrentMapCacheManager -> ConcurrentMapCache -> ConcurrentMap
 * -    通过ConcurrentMapCacheManager去管理ConcurrentMapCache，在ConcurrentMapCache中保存了一个ConcurrentMap
 * -    在ConcurrentMap中真实的以KV的形式保存了缓存数据
 *
 * @see EmployeeService 关于缓存详细的说明在EmployeeService类中
 * <p>
 * 三、开发中，使用缓存中间件：Redis、EhCache、Memcached 等
 * - 整合Redis作为缓存
 * -    Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件
 * -    1. 使用Docker安装Redis
 * -    2. 引入Redis的starter
 * -    3. 配置Redis
 * -    4. 测试Redis作为缓存
 * -        原理：CacheManager -> Cache -> 通过Cache组件来实际的操作缓存，存取数据
 * -        1) 引入Redis的starter后，会向容器中自动配置一个{@link RedisCacheManager}，其他的CacheManager就不会生效了
 * -        2) RedisCacheManager 自动创建一个 RedisCache 作为缓存组件; RedisCache 通过操作Redis来缓存数据
 * -        3) 默认保存数据K-V都是Object时，利用JDK的序列化机制来保存；如何自动保存为JSON呢?
 * -            a. 引入了Redis的starter后，CacheManager变为RedisCacheManager
 * -            b. 默认创建的RedisCacheManager，使用RedisTemplate<Object, Object>来操作Redis缓存
 * -            c. RedisTemplate<Object, Object> 中默认使用JDK的序列化机制
 * -        4) 自定义CacheManager
 * -
 */
@EnableCaching
@MapperScan(value = {"cn.llman.cache.mapper"})
@SpringBootApplication
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}

