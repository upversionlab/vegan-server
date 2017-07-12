package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Company company = (Company) obj;
        return Objects.equals(getId(), company.getId())
                && Objects.equals(isVegan(), company.isVegan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isVegan());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("vegan", isVegan())
                .toString();
    }
}
