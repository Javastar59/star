package com.star.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.star.common.api.ContentApi;
import com.star.common.domain.admin.Content;
import com.star.service.core.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0")
@Component
public class ContentProvider implements ContentApi {

    private final ContentService contentService;

    @Autowired
    public ContentProvider(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public Content save(Content content) {
        return contentService.save(content);
    }

    @Override
    public List<Object> findAll() {
        return contentService.findAll();
    }
}
