package com.star.common.domain.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDomain implements Serializable {

    private String id;

    private String createTime;

    private String updateTime;
}
