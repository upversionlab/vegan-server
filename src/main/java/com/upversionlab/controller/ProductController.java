package com.upversionlab.controller;

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

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getProducts() {
        return products.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@RequestParam int id) {
        return products.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createProduct(@RequestBody Product product) {
        product.setId(products.size());
        products.put(product.getId(), product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateProducts(@RequestParam int id, @RequestBody Product newProduct) {
        Product product = products.get(id);
        product.update(newProduct);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam int id) {
        products.remove(id);
    }
}
