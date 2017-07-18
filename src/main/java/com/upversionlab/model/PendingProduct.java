package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "pending_products")
public class PendingProduct {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "upload_date")
    private String uploadDate;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "nutritional_facts")
    private String nutritionalFacts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNutritionalFacts() {
        return nutritionalFacts;
    }

    public void setNutritionalFacts(String nutritionalFacts) {
        this.nutritionalFacts = nutritionalFacts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PendingProduct that = (PendingProduct) obj;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getUploadDate(), that.getUploadDate())
                && Objects.equals(getBarcode(), that.getBarcode())
                && Objects.equals(getIngredients(), that.getIngredients())
                && Objects.equals(getNutritionalFacts(), that.getNutritionalFacts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getUploadDate(),
                getBarcode(),
                getIngredients(),
                getNutritionalFacts()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("uploadDate", getUploadDate())
                .add("barcode", getBarcode())
                .add("ingredients", getIngredients())
                .add("nutritionalFacts", getNutritionalFacts())
                .toString();
    }
}
