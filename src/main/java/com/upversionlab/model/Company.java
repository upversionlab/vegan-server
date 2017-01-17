package com.upversionlab.model;

import java.util.List;

/**
 * Created by rborcat on 1/3/2017.
 */
public class Company {
    private int id;
    private Boolean vegan;
    private List<Product> products;

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean isVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void update(Company newCompany) {
        if (newCompany.isVegan() != null) {
            setVegan(newCompany.isVegan());
        }
    }
}
