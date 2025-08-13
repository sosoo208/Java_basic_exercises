package com.rookies4.MySpringbootLab.vo;

import com.rookies4.MySpringbootLab.entity.MyEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class DevConfig {
    @Bean
    public MyEnvironment myEnvironment() {
        MyEnvironment env = new MyEnvironment();
        env.setMode("개발환경");
        return env;
    }
}
