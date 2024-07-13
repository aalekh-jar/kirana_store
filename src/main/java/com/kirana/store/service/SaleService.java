package com.kirana.store.service;

import com.kirana.store.collections.Sale;
import com.kirana.store.entities.SaleData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaleService {

    ResponseEntity<?> save(SaleData saleData);

    List<Sale> findAllSaleForCustomer(String customerId);
}
