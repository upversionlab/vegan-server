package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "brand")
public class Brand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "animal_tests")
    private Boolean animalTests;

    @Column(name = "animal_resources")
    private Boolean animalResources;

    @Column(name = "website")
    private String website;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Boolean getAnimalTests() {
        return animalTests;
    }

    public void setAnimalTests(Boolean animalTests) {
        this.animalTests = animalTests;
    }

    public Boolean getAnimalResources() {
        return animalResources;
    }

    public void setAnimalResources(Boolean animalResources) {
        this.animalResources = animalResources;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Brand that = (Brand) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getLogoUrl(), that.getLogoUrl())
                && Objects.equals(getAnimalTests(), that.getAnimalTests())
                && Objects.equals(getAnimalResources(), that.getAnimalResources())
                && Objects.equals(getWebsite(), that.getWebsite())
                && Objects.equals(getCompany(), that.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                getName(),
                getLogoUrl(),
                getAnimalTests(),
                getAnimalResources(),
                getWebsite(),
                getCompany()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", getName())
                .add("logoUrl", getLogoUrl())
                .add("animalTests", getAnimalTests())
                .add("animalResources", getAnimalResources())
                .add("website", getWebsite())
                .add("company", getCompany())
                .toString();
    }
}
