package com.upversionlab.repository;

import com.upversionlab.model.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "brands", path = "brands")
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
}
