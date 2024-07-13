package com.kirana.store.repository;

import com.kirana.store.collections.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    List<Sale> findAllByCustomerId(String customerId);

}
