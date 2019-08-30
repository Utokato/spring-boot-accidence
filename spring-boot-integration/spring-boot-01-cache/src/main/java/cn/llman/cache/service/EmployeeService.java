package cn.llman.cache.service;

import cn.llman.cache.bean.Employee;
import cn.llman.cache.config.MyCacheConfig;
import cn.llman.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Service;

/**
 * 使用{@link CacheConfig} 统一配置缓存的公共配置
 *
 * @author
 * @date 2019/1/16
 */
@CacheConfig(cacheNames = {"emp"})
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 使用{@link Cacheable}注解
     * 将方法运行的结果进行缓存，之后在需要相同的结果，就直接从缓存中获取，不用调用该方法
     * <p>
     * CacheManager管理多个Cache组件，对缓存的真正的CRUD操作在Cache组件中，每一个Cache有一个自己唯一的名字
     * -    cacheNames/value：指定缓存组件(Cache)的名字；将方法的返回结果放在哪一个缓存中，支持数组的形式，可以指定多个缓存
     * -    key：缓存数据时使用的key，可以使用该属性来指定。默认使用方法参数的值。也可以使用SpEL表达式来指定
     * -    keyGenerator：key的生成策略，可以自定义，参看 {@link MyCacheConfig#myKeyGenerator()}
     * -        key/keyGenerator：二选一使用
     * -    cacheManager：指定缓存管理器
     * -    cacheResolver：指定缓存解析器
     * -    condition：指定符合条件下，才进行缓存，支持SpEL表达式
     * -    unless：否定缓存，当unless返回的值为true，方法的返回值就不会被缓存
     * -        与condition判断刚好相反。unless可以获取到返回的结果进行判断
     * -    sync：指定缓存是否启用异步
     * <p>
     * 原理：
     * 1. 自动配置类：CacheAutoConfiguration
     * -    自动配置类通过@Import(CacheConfigurationImportSelector.class)向容器中导入组件
     * 2. 导入的缓存配置类有：10个
     * -    org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     * -    org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration [默认使用]
     * -    org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     * 3. 哪个配置类能默认生效?
     * -    SimpleCacheConfiguration
     * 4. SimpleCacheConfiguration给容器中注册了一个缓存管理器：{@link ConcurrentMapCacheManager}
     * 5. ConcurrentMapCacheManager可以获取和创建{@link ConcurrentMapCache}类型的缓存组件
     * 6. ConcurrentMapCache可以将数据保存在一个ConcurrentMap<Object, Object>中
     * <p>
     * 缓存的执行流程：
     * 1. 在方法运行之前，先去查询Cache(缓存组件)，按照cacheNames指定的名称去获取
     * -   (CacheManager先获取相应的缓存)，第一次获取缓存信息，获取不到，就会自动创建一个Cache组件
     * -    默认创建的Cache组件是ConcurrentMapCache
     * -    并将这个Cache组件放置在一个Map中
     * 2. 在ConcurrentMapCache中查找缓存的内容，使用一个key，key默认是方法的参数
     * -    这个key是按照一定的策略生成：默认使用{@link KeyGenerator}生成的
     * -    KeyGenerator是一个接口，默认使用子类{@link SimpleKeyGenerator}生成key
     * -        SimpleKeyGenerator生成key的策略为：
     * -            如果方法没有参数，key = new SimpleKey();
     * -            如果方法有一个参数，key就等于这个参数
     * -            如果方法有多个参数，key = new SimpleKey(params);
     * 3. 在缓存中没有查询到内容，就调用目标方法
     * 4. 将模板方法返回的结果，放进缓存中
     * <p>
     * 使用@Cacheable标注的方法在执行前，先去缓存中检查是否有该数据，默认使用方法的参数作为key去查询
     * 如果查询不到，就执行目标方法，并将方法返回的数据存入到缓存中；之后再次调用，就直接从缓存中获取数据，不用执行方法
     * <p>
     * 核心：
     * -    1.通过CacheManager[默认：ConcurrentMapCacheManager]按照名字获取到相应的Cache[默认：ConcurrentMapCache]组件
     * -    2.通过keyGenerator[默认：SimpleKeyGenerator]去生成key，然后去ConcurrentMapCache中查询
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}, key = "#id",/*keyGenerator = "myKeyGenerator",*/ condition = "#id>0", unless = "#result==null")
    public Employee getEmp(Integer id) {
        System.out.println("[查询用户] id: " + id);
        return employeeMapper.getEmpById(id);
    }


    /**
     * 使用{@link CachePut}，既调用方法，又更新缓存数据
     * 修改了数据库的某个方法，同时更新缓存
     * -    <b>为了能够达到缓存的同步更新，每次存入缓存和更新缓存中的key要保持一致</b>
     * <p>
     * 运行时机：
     * 1. 先调用目标方法
     * 2. 将目标方法的结果缓存起来
     * <p>
     * 测试步骤：
     * 1. 查询1号员工，查询到的员工信息会存到缓存中
     * -    查询会向缓存中存入的KV：key是员工的id，value是员工信息
     * 2. 再次查询，会从缓存中获取，员工信息还是之前的信息
     * 3. 更新1号员工
     * -    更新也会向缓存中存入KV：key是传入的employee对象，value是返回的employee对象
     * 4. 再次查询1号员工：
     * -    应该是更新后的员工信息
     * -    为什么没有更新呢?
     * -        因为两次存入的key不一样，所以通过查询存在缓存中的员工信息没有得到更新，所以查询的还是更新前的员工信息
     * -    怎么解决这个问题呢?
     * -        指定更新时放入缓存中的key和查询时放入缓存中的key为相同的，即都使用员工的id
     * -        key = "#employee.id" 或者使用  key = "#result.id"
     * <p>
     * -    这里需要注意的一点是，{@link Cacheable}注解是<b>不能</b>使用key = "#result.id" 来指定key
     * -        这是因为@Cacheable会先执行查询缓存中的数据，再去执行目标方法，如果使用#result.id，此时方法还没有执行，key就为空
     * -        但@CachePut是先执行目标方法，执行完毕后，再去更新缓存。此时已经有返回值了，所以使用#result.id能够获取key
     * -        这点细微的差别，完全是由于两种缓存机制的执行顺序不同导致的，需要额外关注
     */
    @CachePut(cacheNames = {"emp"}, key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("[更新用户] 用户信息: " + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 使用{@link CacheEvict}来清除缓存
     * -    key：指定删除哪一个缓存
     * -    allEntries = true; 使用该属性可以清除该缓存中所有的KV，默认是false
     * -    beforeInvocation = false; 指定清除缓存是否在方法调用前执行；默认false表示在方法执行后再去清除缓存
     * -        默认false情况下，如果方法执行出现异常，缓存就不会被清除
     * -        如果改为true，不论方法执行是否出现异常，都会直接清除缓存
     */
    @CacheEvict(cacheNames = {"emp"}, /*key = "#id",*/ allEntries = true, beforeInvocation = false)
    public void deleteEmp(Integer id) {
        System.out.println("[删除用户] id: " + id);
        // employeeMapper.deleteEmpById(id);
    }


    /**
     * 使用{@link Caching}来定义复杂的缓存规则
     *
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(cacheNames = "emp", key = "#result.id"),
                    @CachePut(cacheNames = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        System.out.println("[查询用户] lastName: " + lastName);
        return employeeMapper.getEmpByLastName(lastName);
    }

}
