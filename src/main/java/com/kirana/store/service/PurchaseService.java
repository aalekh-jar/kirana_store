package com.kirana.store.service;

import com.kirana.store.collections.Purchase;
import com.kirana.store.responses.SuccessCreated;

import java.util.List;

public interface PurchaseService {

    SuccessCreated save(Purchase product);

    List<Purchase> findAllByProductId(String productId);

}
