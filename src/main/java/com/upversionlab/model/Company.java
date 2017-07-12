package com.upversionlab.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "company")
public class Company {

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

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Brand> brands;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_certification",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "certification_id")
    )
    private List<Certification> certifications;

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

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Company that = (Company) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getLogoUrl(), that.getLogoUrl())
                && Objects.equals(getAnimalTests(), that.getAnimalTests())
                && Objects.equals(getAnimalResources(), that.getAnimalResources())
                && Objects.equals(getWebsite(), that.getWebsite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                getName(),
                getLogoUrl(),
                getAnimalTests(),
                getAnimalResources(),
                getWebsite()
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
                .toString();
    }
}
