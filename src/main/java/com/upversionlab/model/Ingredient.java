package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Created by rborcat on 1/3/2017.
 */
public class Ingredient {

    private int id;
    private Boolean vegan;
    private Product product;

    public Ingredient() {}

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void update(Ingredient newIngredient) {
        if (newIngredient.isVegan() != null) {
            setVegan(newIngredient.isVegan());
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

        Ingredient ingredient = (Ingredient) obj;
        return Objects.equals(getId(), ingredient.getId())
                && Objects.equals(isVegan(), ingredient.isVegan());
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
