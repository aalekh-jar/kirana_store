package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Customer;
import com.kirana.store.repository.CustomerRepository;
import com.kirana.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public String save(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Override
    public List<Customer> findAllByStoreId(String name) {
        return customerRepository.findAllByStoreId(name);
    }
}
