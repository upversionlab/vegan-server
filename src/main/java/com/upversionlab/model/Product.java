package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "product")
public class Product {

    public enum ProductType {
        FOOD,
        CLEANING,
        HYGIENE
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "package_image_url")
    private String packageImageUrl;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductIngredient> productIngredients;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<NutritionFact> nutritionFacts;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageImageUrl() {
        return packageImageUrl;
    }

    public void setPackageImageUrl(String packageImageUrl) {
        this.packageImageUrl = packageImageUrl;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<ProductIngredient> getProductIngredients() {
        return productIngredients;
    }

    public void setProductIngredients(List<ProductIngredient> productIngredients) {
        this.productIngredients = productIngredients;
    }

    public List<NutritionFact> getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(List<NutritionFact> nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product that = (Product) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(getBarcode(), that.getBarcode())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getPackageImageUrl(), that.getPackageImageUrl())
                && Objects.equals(getType(), that.getType())
                && Objects.equals(getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                getBarcode(),
                getName(),
                getPackageImageUrl(),
                getType(),
                getBrand()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("barcode", getBarcode())
                .add("name", getName())
                .add("packageImageUrl", getPackageImageUrl())
                .add("type", getType())
                .add("brand", getBrand())
                .toString();
    }
}
