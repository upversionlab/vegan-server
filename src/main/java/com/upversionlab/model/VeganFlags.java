package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "vegan_flags")
public class VeganFlags {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "meat")
    private boolean meat;

    @Column(name = "egg")
    private boolean egg;

    @Column(name = "milk")
    private boolean milk;

    @Column(name = "honey")
    private boolean honey;

    @Column(name = "gluten")
    private boolean gluten;

    @Column(name = "organic")
    private boolean organic;

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isHoney() {
        return honey;
    }

    public void setHoney(boolean honey) {
        this.honey = honey;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean organic) {
        this.organic = organic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        VeganFlags that = (VeganFlags) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(isMeat(), that.isMeat())
                && Objects.equals(isEgg(), that.isEgg())
                && Objects.equals(isMilk(), that.isMilk())
                && Objects.equals(isHoney(), that.isHoney())
                && Objects.equals(isGluten(), that.isGluten())
                && Objects.equals(isOrganic(), that.isOrganic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                isMeat(),
                isEgg(),
                isMilk(),
                isHoney(),
                isGluten(),
                isOrganic()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("meat", isMeat())
                .add("egg", isEgg())
                .add("milk", isMilk())
                .add("honey", isHoney())
                .add("gluten", isGluten())
                .add("organic", isOrganic())
                .toString();
    }
}
