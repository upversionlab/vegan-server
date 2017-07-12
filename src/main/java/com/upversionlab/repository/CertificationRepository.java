package com.upversionlab.repository;

import com.upversionlab.model.Certification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "certifications", path = "certifications")
public interface CertificationRepository extends PagingAndSortingRepository<Certification, Long> {
}
