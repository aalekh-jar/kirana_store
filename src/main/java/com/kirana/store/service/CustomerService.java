package com.kirana.store.service;

import com.kirana.store.collections.Customer;
import com.kirana.store.responses.SuccessCreated;

import java.util.List;

public interface CustomerService {

    SuccessCreated save(Customer customer);

    List<Customer> findAllByStoreId(String storeId);

    Customer findByCustomerId(String id);

}
