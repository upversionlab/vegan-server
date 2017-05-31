package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ingredient")
public class Ingredient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "vegan_flags_id", referencedColumnName = "id")
    private VeganFlags veganFlags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Ingredient that = (Ingredient) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getVeganFlags(), that.getVeganFlags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                getName(),
                getVeganFlags()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", getName())
                .add("veganFlags", getVeganFlags())
                .toString();
    }
}
