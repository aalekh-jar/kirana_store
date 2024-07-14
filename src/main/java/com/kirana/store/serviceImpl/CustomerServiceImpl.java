package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Customer;
import com.kirana.store.redis.entity.CustomerRedis;
import com.kirana.store.redis.repository.CustomerRedisRepository;
import com.kirana.store.repository.CustomerRepository;
import com.kirana.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerRedisRepository customerRedisRepository;

    @Override
    public String save(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Override
    public List<Customer> findAllByStoreId(String name) {
        return customerRepository.findAllByStoreId(name);
    }

    @Cacheable
    @Override
    public Customer findByCustomerId(String id) {
        Optional<CustomerRedis> cacheCustomer
                = customerRedisRepository.findById(String.valueOf(id));
        if(cacheCustomer.isPresent()) {
            return cacheCustomer.get().mapToCustomer();
        }
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(value -> customerRedisRepository.save(value.mapToCache()));
        return customer.orElse(null);
    }
}
