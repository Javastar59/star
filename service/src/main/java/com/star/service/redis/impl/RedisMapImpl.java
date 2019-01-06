package com.star.service.redis.impl;

import com.star.service.redis.RedisMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class RedisMapImpl implements RedisMap {

    private final RedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisMapImpl(RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object put(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    @Override
    public void clear() {
    }
}
