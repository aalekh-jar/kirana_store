package com.kirana.store.repository;

import com.kirana.store.collections.Product;
import com.kirana.store.collections.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {

    Purchase findByProductId(String productId);

    List<Purchase> findAllByProductId(String productId);

}
