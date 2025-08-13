package com.rookies4.MySpringbootLab.vo;

import com.rookies4.MySpringbootLab.entity.MyEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdConfig {
    @Bean
    public MyEnvironment myEnvironment() {
        MyEnvironment env = new MyEnvironment();
        env.setMode("운영환경");
        return env;
    }
}
