package com.upversionlab.configuration;

import com.upversionlab.model.Ingredient;
import com.upversionlab.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class VeganServerConfiguration {
    @Bean
    public Map<Integer, Product> products() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, Ingredient> ingredients() {
        return new HashMap<>();
    }

    @Bean
    public String storageLocation(@Value("${vegan-server.storage.location}") String location) { return location; };

    @Bean
    public Calendar calendar() {
        return Calendar.getInstance();
    }

}
