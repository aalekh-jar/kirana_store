package com.kirana.store.controller;

import com.kirana.store.collections.Product;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.exceptions.NoProductsFoundException;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public String save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getProductsList(@RequestParam String storeId) {
        List<Product> products = productService.getAllProductsFromGivenStore(storeId);
        if (products.isEmpty()) {
            throw new NoProductsFoundException(ErrorStrings.NO_PRODUCTS_FOUND_FOR_STORE);
        }
        return products;
    }
}
