package com.kirana.store.controller;

import com.kirana.store.collections.Product;
import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.dto.ProductDto;
import com.kirana.store.exceptions.DataValidationError;
import com.kirana.store.exceptions.NoProductsFoundException;
import com.kirana.store.repository.OnboardingRepository;
import com.kirana.store.service.OnBoardingService;
import com.kirana.store.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OnBoardingService onBoardingService;

    @PostMapping
    public String save(
            @RequestBody ProductDto productDto,
            @RequestHeader(Constants.X_USER_ID) String userId) {
        if (userId == null || userId.isEmpty() || !productDto.isValid()) {
            throw new DataValidationError("Invalid Data");
        }
        if (!onBoardingService.isStoreBelongToUser(productDto.getStoreId(), userId)) {
            throw new DataValidationError("Store is not mapped to the user");
        }
        return productService.save(productDto.mapToProduct());
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
