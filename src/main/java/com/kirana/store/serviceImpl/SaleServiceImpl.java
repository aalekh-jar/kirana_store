package com.kirana.store.serviceImpl;

import com.kirana.store.collections.*;
import com.kirana.store.constants.ErrorCodes;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.constants.SuccessCodes;
import com.kirana.store.constants.SuccessStrings;
import com.kirana.store.entities.SaleData;
import com.kirana.store.exceptions.GeneralException;
import com.kirana.store.exceptions.NoCustomerRegisteredException;
import com.kirana.store.exceptions.NoProductsFoundException;
import com.kirana.store.exceptions.NotEnoughStockException;
import com.kirana.store.repository.*;
import com.kirana.store.responses.SuccessCreated;
import com.kirana.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Credit
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository salesRepository;

    @Autowired
    SaleDetailsRepository saleDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> save(SaleData saleData) {

        if (saleData.productId == null ||
                saleData.productId.isEmpty() ||
                saleData.quantity <= 0 ||
                saleData.pricePerUnit <= 0) {
            throw new GeneralException(ErrorStrings.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_CODE);
        }

        Optional<Product> product = productRepository.findById(saleData.productId);

        if (product.isEmpty()) {
            throw new NoProductsFoundException(ErrorStrings.NO_PRODUCT_FOUND);
        }

        if (!Objects.equals(product.get().getStoreId(), saleData.storeId)) {
            throw new NoProductsFoundException(ErrorStrings.PRODUCT_NOT_FOUND_IN_GIVEN_STORE);
        }

        if (saleData.customerId == null || saleData.customerId.isEmpty()) {
            throw new GeneralException(ErrorStrings.CUSTOMER_ID_NOT_PRESENT, ErrorCodes.DATA_MISSING_CODE);
        }
        // Check if customer is legit or not
        Optional<Customer> customer = customerRepository.findById(saleData.customerId);
        if (customer.isEmpty() || !Objects.equals(customer.get().getStoreId(), saleData.storeId)) {
            throw new NoCustomerRegisteredException(ErrorStrings.NO_CUSTOMERS_REGISTERED_FOR_STORE);
        }

        // Check the quantity
        Optional<Stock> stock = stockRepository.findByProductId(saleData.productId);
        if (stock.isEmpty() || stock.get().getQuantity() < saleData.quantity) {
            throw new NotEnoughStockException(ErrorStrings.NOT_ENOUGH_STOCK);
        }

        // Update quantity
        Stock updatedStock = stock.get();
        updatedStock.setQuantity(updatedStock.getQuantity() - saleData.quantity);
        stockRepository.save(updatedStock);

        // Create Sale Entry
        Sale sale = new Sale(saleData.customerId);
        String saleId = salesRepository.save(sale).getId();

        // Create SaleDetails Entry
        SaleDetails saleDetails = new SaleDetails(
                saleData.productId,
                saleId,
                saleData.pricePerUnit,
                saleData.quantity
        );

        saleDetailsRepository.save(saleDetails);
        SuccessCreated response = new SuccessCreated();
        response.setCode(SuccessCodes.SUCCESS_CREATED);
        response.setMessage(SuccessStrings.SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<Sale> findAllSaleForCustomer(String customerId) {
        return salesRepository.findAllByCustomerId(customerId);
    }
}
