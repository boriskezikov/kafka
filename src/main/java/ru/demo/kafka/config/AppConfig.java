package ru.demo.kafka.config;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RandomStringGenerator randomStringGenerator() {
        return new RandomStringGenerator
                .Builder()
                .withinRange('0', 'z')
                .build();
    }
}
