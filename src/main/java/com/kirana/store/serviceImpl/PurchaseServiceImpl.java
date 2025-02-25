package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Product;
import com.kirana.store.collections.Purchase;
import com.kirana.store.collections.Stock;
import com.kirana.store.collections.Store;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.constants.SuccessCodes;
import com.kirana.store.constants.SuccessStrings;
import com.kirana.store.exceptions.NoProductsFoundException;
import com.kirana.store.exceptions.NoStoreRegistrationFoundException;
import com.kirana.store.repository.OnboardingRepository;
import com.kirana.store.repository.ProductRepository;
import com.kirana.store.repository.PurchaseRepository;
import com.kirana.store.repository.StockRepository;
import com.kirana.store.responses.SuccessCreated;
import com.kirana.store.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Debit
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OnboardingRepository onboardingRepository;

    @Override
    public SuccessCreated save(Purchase purchase) {
        purchaseRepository.save(purchase);
        Optional<Stock> stock = stockRepository.findByProductId(purchase.getProductId());
        if (stock.isPresent()) {
            Stock oldStock = stock.get();
            oldStock.setQuantity(oldStock.getQuantity() + purchase.getQuantity());
            stockRepository.save(oldStock);
        } else {
            Stock newStock = new Stock(
                    purchase.getProductId(),
                    purchase.getQuantity(),
                    System.currentTimeMillis()
            );
            stockRepository.save(newStock);
        }
        SuccessCreated response = new SuccessCreated();
        response.setCode(SuccessCodes.SUCCESS_CREATED);
        response.setMessage(SuccessStrings.SUCCESSFULLY_CREATED);
        return response;
    }

    @Override
    public List<Purchase> findAllByProductId(String productId) {
        return purchaseRepository.findAllByProductId(productId);
    }

}
