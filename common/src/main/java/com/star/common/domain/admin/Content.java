package com.star.common.domain.admin;

import com.star.common.domain.base.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content extends BaseDomain {

    private String title;

    private String content;

    private String picture;
}
