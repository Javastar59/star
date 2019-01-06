package com.star.service.redis;

public interface RedisMap {

    int size();

    boolean isEmpty();

    Object put(Object key, Object value);

    void clear();
}
