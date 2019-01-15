package com.star.common.domain;

import com.star.common.domain.base.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomain {

    private String userName;

    private String passWord;

}
