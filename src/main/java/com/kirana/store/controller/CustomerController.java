package com.kirana.store.controller;

import com.kirana.store.collections.Customer;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.exceptions.NoCustomerRegisteredException;
import com.kirana.store.service.CustomerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public String save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getProductsList(String storeId) {
        List<Customer> customers = customerService.findAllByStoreId(storeId);
        if (customers.isEmpty()) {
            throw new NoCustomerRegisteredException(ErrorStrings.NO_CUSTOMERS_REGISTERED_FOR_STORE);
        }
        return customers;
    }

}
