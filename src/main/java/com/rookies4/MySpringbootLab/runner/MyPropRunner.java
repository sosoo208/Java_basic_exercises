package com.rookies4.MySpringbootLab.runner;

import com.rookies4.MySpringbootLab.property.MyPropProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyPropRunner implements ApplicationRunner {
    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private MyPropProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Username: {}", username);
        log.info("Port: {}", port);
    }
}
