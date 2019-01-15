package com.star.service.core;

import com.star.common.domain.admin.Content;

import java.util.List;

public interface ContentService {

    Content save(Content content);

    List<Object> findAll();
}
