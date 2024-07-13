package com.kirana.store.repository;

import com.kirana.store.collections.SaleDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailsRepository extends MongoRepository<SaleDetails, String> {

}
