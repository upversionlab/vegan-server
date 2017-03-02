package com.upversionlab.controller;

import com.upversionlab.exception.EntityNotFoundException;
import com.upversionlab.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * Created by rborcat on 1/3/2017.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String PRODUCT_RESOURCE_NAME = Product.class.getSimpleName();

    private final Map<Integer, Product> products;

    @Autowired
    public ProductController(Map<Integer, Product> products) {
        this.products = products;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getProducts() {
        return products.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) {
        return getProductById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product createProduct(@RequestBody Product product) {
        product.setId(products.size());
        products.put(product.getId(), product);
        return product;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Product updateProducts(@PathVariable int id, @RequestBody Product newProduct) {
        Product product = getProductById(id);
        product.update(newProduct);
        return product;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id) {
        products.remove(getProductById(id));
    }

    private Product getProductById(int id) {
        Product product = products.get(id);
        if (product != null) {
            return product;
        } else {
            throw new EntityNotFoundException(PRODUCT_RESOURCE_NAME + " " + id + " not found!");
        }
    }
}
