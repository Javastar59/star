package com.star.common.api;

import com.star.common.domain.admin.Content;

import java.util.List;

public interface ContentApi {

    Content save(Content content);

    List<Object> findAll();
}
