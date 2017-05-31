package com.upversionlab.repository;

import com.upversionlab.model.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ingredients", path = "ingredients")
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {
}
