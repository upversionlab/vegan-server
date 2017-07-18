package com.upversionlab.repository;

import com.upversionlab.model.PendingProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PendingProductRepository extends PagingAndSortingRepository<PendingProduct, Long> {

    PendingProduct findByIdAndUploadDate(Long id, String uploadDate);

    void deleteByIdAndUploadDate(Long id, String uploadDate);

}