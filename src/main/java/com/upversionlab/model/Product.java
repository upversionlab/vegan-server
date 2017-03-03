package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

/**
 * Created by rborcat on 1/3/2017.
 */
public class Product {
    private int id;
    private Company company;
    private List<Ingredient> ingredients;

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void update(Product newProduct) {
        if (newProduct.getCompany() != null) {
            setCompany(newProduct.getCompany());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product product = (Product) obj;
        return Objects.equals(getId(), product.getId())
                && Objects.equals(getCompany(), product.getCompany())
                && Objects.equals(getIngredients(), product.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompany(), getIngredients());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("company", getCompany())
                .add("ingredients", getIngredients())
                .toString();
    }
}
