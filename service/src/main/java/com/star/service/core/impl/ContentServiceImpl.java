package com.star.service.core.impl;

import com.star.common.domain.admin.Content;
import com.star.common.utils.IDUtils;
import com.star.common.utils.Time8Utils;
import com.star.service.core.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Content save(Content content) {
        String id = IDUtils.snowflake();
        content.setId(id);
        content.setCreateTime(Time8Utils.formatNow());
        content.setUpdateTime(Time8Utils.formatNow());
        redisTemplate.opsForHash().put("content", id, content);
        return content;
    }

    @Override
    public List<Object> findAll() {
        return redisTemplate.opsForHash().values("content");
    }
}
