package com.kirana.store.controller;

import com.kirana.store.collections.Product;
import com.kirana.store.collections.Purchase;
import com.kirana.store.collections.Store;
import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.dto.PurchaseDto;
import com.kirana.store.exceptions.DataValidationError;
import com.kirana.store.exceptions.NoProductsFoundException;
import com.kirana.store.exceptions.NoPurchasesFoundException;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.service.OnBoardingService;
import com.kirana.store.service.ProductService;
import com.kirana.store.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    OnBoardingService onBoardingService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody PurchaseDto purchaseDto,
            @RequestHeader(Constants.X_USER_ID) String userId) {
        if (userId == null || userId.isEmpty() || !purchaseDto.isValid()) {
            throw new DataValidationError("Invalid Data");
        }
        Product product = productService.getByProductId(purchaseDto.getProductId());
        if (product == null) {
            throw new NoProductsFoundException(ErrorStrings.NO_PRODUCT_FOUND);
        }
        Store store = onBoardingService.getByStoreId(product.getStoreId());
        if (store == null) {
            // This can be avoided as product will always have a valid store
            throw new NoStoreRegistrationFoundException(ErrorStrings.STORE_DOES_NOT_EXIST);
        }
        if (!onBoardingService.isStoreBelongToUser(store.getId(), userId)) {
            throw new DataValidationError("Store is not mapped to a store you belong");
        }
        return purchaseService.save(purchaseDto.mapToPurchase());
    }

    @GetMapping
    public List<Purchase> getAllPurchasesForProduct(@RequestParam String productId) {
        List<Purchase> purchases = purchaseService.findAllByProductId(productId);
        if (purchases.isEmpty()) {
            throw new NoPurchasesFoundException(ErrorStrings.NO_PURCHASE_FOUND_FOR_PRODUCT);
        }
        return purchases;
    }
}
