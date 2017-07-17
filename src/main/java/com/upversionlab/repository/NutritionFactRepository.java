package com.upversionlab.repository;

import com.upversionlab.model.NutritionFact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "nutritionFacts", path = "nutritionFacts")
public interface NutritionFactRepository extends PagingAndSortingRepository<NutritionFact, Long> {
}
