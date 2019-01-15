package com.star.common.domain.admin;

import com.star.common.domain.base.BaseDomain;
import lombok.Data;

@Data
public class Content extends BaseDomain {

    private String title;

    private String content;

    private String picture;
}
