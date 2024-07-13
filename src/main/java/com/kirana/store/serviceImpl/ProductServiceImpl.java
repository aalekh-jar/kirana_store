package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Product;
import com.kirana.store.collections.Store;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.repository.OnboardingRepository;
import com.kirana.store.repository.ProductRepository;
import com.kirana.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OnboardingRepository onboardingRepository;

    @Override
    public String save(Product product) {
        Optional<Store> store = onboardingRepository.findById(product.getStoreId());
        if (store.isEmpty()) {
            throw new NoStoreRegistrationFoundException(ErrorStrings.INVALID_STORE);
        }
        return productRepository.save(product).getId();
    }

    @Override
    public List<Product> getNameStartsWith(String name) {
        return productRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Product> getAllProductsFromGivenStore(String storeId) {
        return productRepository.findAllByStoreId(storeId);
    }

}
