package com.kirana.store.controller;

import com.kirana.store.collections.Customer;
import com.kirana.store.constants.Constants;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.dto.CustomerDto;
import com.kirana.store.exceptions.DataValidationError;
import com.kirana.store.exceptions.NoCustomerRegisteredException;
import com.kirana.store.service.CustomerService;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    OnBoardingService onBoardingService;

    @PostMapping
    public String save(
            @RequestBody CustomerDto customerDto,
            @RequestHeader(Constants.X_USER_ID) String userId) {
        if(userId == null || userId.isEmpty() || !customerDto.isValid()) {
            throw new DataValidationError("Invalid Data");
        }
        if (!onBoardingService.isStoreBelongToUser(customerDto.getStoreId(), userId)) {
            throw new DataValidationError("Store is not mapped to the user");
        }
        return customerService.save(customerDto.mapToCustomer());
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
