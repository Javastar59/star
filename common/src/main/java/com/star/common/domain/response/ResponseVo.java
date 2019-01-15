package com.star.common.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseVo {

    private int status;

    private Object data;

    public ResponseVo(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static ResponseVo success(Object data) {
        return new ResponseVo(HttpStatus.OK.value(), data);
    }

    public static ResponseVo fail() {
        return new ResponseVo(HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }
}
