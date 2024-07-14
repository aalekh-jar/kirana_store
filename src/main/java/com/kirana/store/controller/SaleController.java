package com.kirana.store.controller;

import com.kirana.store.collections.Customer;
import com.kirana.store.collections.Product;
import com.kirana.store.collections.Sale;
import com.kirana.store.collections.Store;
import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.dto.SalesDto;
import com.kirana.store.exceptions.*;
import com.kirana.store.responses.SuccessCreated;
import com.kirana.store.service.CustomerService;
import com.kirana.store.service.OnBoardingService;
import com.kirana.store.service.ProductService;
import com.kirana.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    OnBoardingService onBoardingService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<SuccessCreated> recordSale(
            @RequestBody SalesDto salesDto,
            @RequestHeader(Constants.X_USER_ID) String userId
    ) {
        if (userId == null || userId.isEmpty() || !salesDto.isValid()) {
            throw new DataValidationError("Invalid Data");
        }
        // Check if product exists or not
        Product product = productService.getByProductId(salesDto.getProductId());
        if (product == null) {
            throw new NoProductsFoundException(ErrorStrings.NO_PRODUCT_FOUND);
        }

        // Check if product belongs to a store or not
        Store store = onBoardingService.getByStoreId(product.getStoreId());
        if (store == null) {
            // This can be avoided as product will always have a valid store
            throw new NoStoreRegistrationFoundException(ErrorStrings.STORE_DOES_NOT_EXIST);
        }

        // Check if Store belongs to the auth user or not
        String storeUserId = store.getUserId();
        if (!storeUserId.equals(userId)) {
            throw new DataValidationError(ErrorStrings.PRODUCT_DONT_BELONG_TO_YOUR_STORE);
        }

        // Check if customer is legit or not and belongs to the store or not
        Customer customer = customerService.findByCustomerId(salesDto.customerId);
        if (customer == null || !store.getId().equals(customer.getStoreId())) {
            throw new NoCustomerRegisteredException(ErrorStrings.NO_CUSTOMERS_REGISTERED_FOR_STORE);
        }

        return new ResponseEntity<>(saleService.save(salesDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Sale> findAllSalesForCustomer(@RequestParam String customerId) {
        List<Sale> sales = saleService.findAllSaleForCustomer(customerId);
        if (sales.isEmpty()) {
            throw new NoSalesFoundException(ErrorStrings.NO_SALES_FOUND_FOR_CUSTOMER);
        }
        return sales;
    }

}
