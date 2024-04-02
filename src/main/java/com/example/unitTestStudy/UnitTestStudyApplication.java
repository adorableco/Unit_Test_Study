package com.example.unitTestStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UnitTestStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestStudyApplication.class, args);
    }

}
