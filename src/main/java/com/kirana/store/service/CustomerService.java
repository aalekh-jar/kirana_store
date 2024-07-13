package com.kirana.store.service;

import com.kirana.store.collections.Customer;

import java.util.List;

public interface CustomerService {

    String save(Customer customer);

    List<Customer> findAllByStoreId(String storeId);

}
