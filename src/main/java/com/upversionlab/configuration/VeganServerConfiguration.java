package com.upversionlab.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class VeganServerConfiguration {

    @Bean
    public String storageLocation(@Value("${vegan-server.storage.location}") String location) {
        return location;
    }

    @Bean
    public Calendar calendar() {
        return Calendar.getInstance();
    }

}
