package com.testconfigurationpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JavaTestConfigurationPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaTestConfigurationPocApplication.class, args);
    }

}
