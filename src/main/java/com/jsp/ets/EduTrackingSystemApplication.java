package com.jsp.ets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class EduTrackingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduTrackingSystemApplication.class, args);
    }

}