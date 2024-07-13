package com.kirana.store.controller;

import com.kirana.store.collections.Sale;
import com.kirana.store.constants.ErrorStrings;
import com.kirana.store.entities.SaleData;
import com.kirana.store.exceptions.NoSalesFoundException;
import com.kirana.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping
    public ResponseEntity<?> recordSale(@RequestBody SaleData saleData) {
        return saleService.save(saleData);
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
