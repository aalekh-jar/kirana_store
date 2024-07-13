package com.kirana.store.service;

import com.kirana.store.collections.Purchase;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseService {

    ResponseEntity<?> save(Purchase product);

    List<Purchase> findAllByProductId(String productId);

}
