package com.star.common.domain.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {

    private Long id;

    private Timestamp createTime;

    private Timestamp updateTime;
}
