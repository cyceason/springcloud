package com.cyc.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
    /**
     * spring boot已经配置了StringRedisTemplate， 可以直接注入
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valOpsStr;

    /**
     * spring boot已经配置了RedisTemplate， 可以直接注入
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valOps;

    public void stringRedisTemplateDemo() {
        valOpsStr.set("xx", "yy");
    }

    public void save(Person person) {
        valOps.set(person.getId(), person);
    }

    public String getString() {
        // 方式一
//        return stringRedisTemplate.opsForValue().get("xx");
        // 获取方式二
        return valOpsStr.get("xx");
    }

    public Person getPerson() {
        return (Person) valOps.get("1");
    }
}
