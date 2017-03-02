package com.upversionlab.configuration;

import com.upversionlab.model.Company;
import com.upversionlab.model.Ingredient;
import com.upversionlab.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vruzeda on 02/03/17.
 */
@Configuration
public class VeganServerConfiguration {

    @Bean
    public Map<Integer, Company> companies() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, Product> products() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, Ingredient> ingredients() {
        return new HashMap<>();
    }

}
