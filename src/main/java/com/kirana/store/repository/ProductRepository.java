package com.kirana.store.repository;

import com.kirana.store.collections.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameStartsWith(String name);

    List<Product> findAllByStoreId(String storeId);

}
