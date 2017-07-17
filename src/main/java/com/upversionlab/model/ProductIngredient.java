package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "product_ingredient")
public class ProductIngredient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;

    @OneToOne
    @JoinColumn(name = "vegan_flags_id", referencedColumnName = "id")
    private VeganFlags veganFlags;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public VeganFlags getVeganFlags() {
        return veganFlags;
    }

    public void setVeganFlags(VeganFlags veganFlags) {
        this.veganFlags = veganFlags;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ProductIngredient that = (ProductIngredient) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(getProduct(), that.getProduct())
                && Objects.equals(getIngredient(), that.getIngredient())
                && Objects.equals(getVeganFlags(), that.getVeganFlags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                getProduct(),
                getIngredient(),
                getVeganFlags()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("product", getProduct())
                .add("ingredient", getIngredient())
                .add("veganFlags", getVeganFlags())
                .toString();
    }
}
