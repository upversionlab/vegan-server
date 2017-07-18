package com.upversionlab.controller;

import com.upversionlab.model.PendingProduct;
import com.upversionlab.repository.PendingProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController("/pendingProducts")
public class PendingProductController {

    private final PendingProductRepository pendingProductRepository;
    private final Calendar calendar;

    @Autowired
    public PendingProductController(PendingProductRepository pendingProductRepository, Calendar calendar) {
        this.pendingProductRepository = pendingProductRepository;
        this.calendar = calendar;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<PendingProduct> getPendingProducts() {
        return pendingProductRepository.findAll();
    }

    @RequestMapping(path = "/{uploadDate}/{id}", method = RequestMethod.GET)
    public PendingProduct getPendingProduct(@PathVariable String uploadDate, @PathVariable Long id) {
        return pendingProductRepository.findByIdAndUploadDate(id, uploadDate);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PendingProduct createPendingProduct(@RequestBody PendingProduct pendingProduct) {
        pendingProduct.setUploadDate(Long.toString(calendar.getTimeInMillis()));
        return pendingProductRepository.save(pendingProduct);
    }

    @RequestMapping(path = "/{uploadDate}/{id}", method = RequestMethod.DELETE)
    public void deletePendingProduct(@PathVariable String uploadDate, @PathVariable Long id) {
        pendingProductRepository.deleteByIdAndUploadDate(id, uploadDate);
    }

}
