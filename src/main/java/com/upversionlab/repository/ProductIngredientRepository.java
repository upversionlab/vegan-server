package com.upversionlab.repository;

import com.upversionlab.model.ProductIngredient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productIngredients", path = "productIngredients")
public interface ProductIngredientRepository extends PagingAndSortingRepository<ProductIngredient, Long> {
}
