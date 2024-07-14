package com.kirana.store.service;

import com.kirana.store.collections.Product;
import com.kirana.store.responses.SuccessCreated;

import java.util.List;

public interface ProductService {

    SuccessCreated save(Product product);

    List<Product> getNameStartsWith(String name);

    List<Product> getAllProductsFromGivenStore(String storeId);

    Product getByProductId(String id);

}
