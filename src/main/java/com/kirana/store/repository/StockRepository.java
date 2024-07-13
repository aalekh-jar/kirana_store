package com.kirana.store.repository;

import com.kirana.store.collections.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    Optional<Stock> findByProductId(String id);

}
