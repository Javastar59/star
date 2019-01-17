package com.star.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.alibaba.dubbo.spring.boot.context.event.DubboBannerApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class ServiceApplication {
    public static void main(String[] args) {
        DubboBannerApplicationListener.setBANNER_MODE(Banner.Mode.OFF);
        SpringApplication.run(ServiceApplication.class, args);
    }

}
