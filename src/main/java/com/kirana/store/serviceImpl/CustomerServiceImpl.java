package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Customer;
import com.kirana.store.repository.CustomerRepository;
import com.kirana.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Customer findByCustomerId(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }
}
