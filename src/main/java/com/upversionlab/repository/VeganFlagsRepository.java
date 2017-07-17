package com.upversionlab.repository;

import com.upversionlab.model.VeganFlags;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "veganFlags", path = "veganFlags")
public interface VeganFlagsRepository extends PagingAndSortingRepository<VeganFlags, Long> {
}
