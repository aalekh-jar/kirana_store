package com.kirana.store.controller;

import com.kirana.store.collections.Purchase;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.exceptions.NoPurchasesFoundException;
import com.kirana.store.responses.SuccessCreated;
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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @GetMapping
    public List<Purchase> getAllPurchasesForProduct(@RequestParam String productId) {
        List<Purchase> purchases = purchaseService.findAllByProductId(productId);
        if(purchases.isEmpty()) {
            throw new NoPurchasesFoundException(ErrorStrings.NO_PURCHASE_FOUND_FOR_PRODUCT);
        }
        return purchases;
    }
}
