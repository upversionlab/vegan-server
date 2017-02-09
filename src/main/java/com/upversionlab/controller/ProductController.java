package com.upversionlab.controller;

import com.upversionlab.exception.EntityNotFoundException;
import com.upversionlab.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rborcat on 1/3/2017.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private Map<Integer, Product> products = new HashMap<>();
    private static final String PRODUCT_RESOURCE_NAME = Product.class.getSimpleName();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getProducts() {
        return products.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) {
        return getProductById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createProduct(@RequestBody Product product) {
        product.setId(products.size());
        products.put(product.getId(), product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateProducts(@PathVariable int id, @RequestBody Product newProduct) {
        Product product = getProductById(id);
        product.update(newProduct);
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
