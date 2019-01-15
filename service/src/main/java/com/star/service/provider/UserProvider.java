package com.star.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.star.common.api.UserApi;
import com.star.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class UserProvider implements UserApi {

    private final UserService userService;

    @Autowired
    public UserProvider(UserService userService) {
        this.userService = userService;
    }

}
