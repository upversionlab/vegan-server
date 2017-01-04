package com.upversionlab.model;

import java.util.List;

/**
 * Created by rborcat on 1/3/2017.
 */
public class Product {
    private int id;
    private Company company;
    private List<Integer> ingredientsId;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Integer> getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(List<Integer> ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public void update(Product newProduct) {
        if (newProduct.getCompany() != null) {
            setCompany(newProduct.getCompany());
        }
    }
}
