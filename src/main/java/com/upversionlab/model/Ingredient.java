package com.upversionlab.model;

/**
 * Created by rborcat on 1/3/2017.
 */
public class Ingredient {
    private int id;
    private Boolean vegan;
    private Product product;

    public Ingredient() {
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
}
